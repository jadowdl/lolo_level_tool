package llt.io

import llt.common.Level

object Rom {
  // TODO(jdowdell) - refactor to clarify that these are for the canonical ROM, not for the patched
  // ROM.
  //
  // I don't know what's up with the ROM I have access to, but it's longer and has a different
  // sha256 than the ones mentioned on datacrystal.
  final val RomSize = 65552
  final val RomSha256 = "914C676959612FC6738A297B6B799DFF848E43DE4E9BD3C9F3C6783EFD059E01"

  final val LevelCount = 50  // lolo1 has 50 levels exactly

  // Magic Numbers from http://datacrystal.romhacking.net/wiki/Adventures_of_Lolo:ROM_map
  // These are all byte offsets from the beginning of the ROM (so I call them "Pointer")
  final val RoomDataOffsetPointer = 0x3A6A
  final val RoomDataLengthPointer = 0x3ACE
  final val RoomDataBankPointer = 0x3B00

  // Also, in my dump, the room data is much further in than what datacrystal mentions.
  final val BaseRoomDataPointer = 0xD010

  // I'm not sure, but I think actually when we go to write the rom back out, we could start
  // from 0x00 rather than 0x10.  But for now, I write to the same parts of the bank that the
  // original ROM has level data in.
  final val MaxBytesPerBank = 0x21000 - BaseRoomDataPointer
}

class Rom (romData: Array[Byte]){
  def getRomData() = romData

  def loadLevel(level: Int): Level = {
    require (0 <= level && level < 50)
    val bankOffset = ( (0xFF & romData(Rom.RoomDataOffsetPointer + level*2)) |
                       ((0xFF & romData(Rom.RoomDataOffsetPointer + level*2 + 1)) << 8))
    val length = romData(Rom.RoomDataLengthPointer + level) & 0xFF
    val bank = romData(Rom.RoomDataBankPointer + level) & 0xFF

    // TODO(jdowdell) - dedup this against writing back the Level later
    // decompress room level data from masterOffset + bank*0x1000 + bankOffset
    val romLevelOffset = Rom.BaseRoomDataPointer + bank*0x1000 + bankOffset
    (Level.fromRomByteBlob(romData.slice(romLevelOffset, romLevelOffset+length))
          .setLevelNumber(level))
  }

  def loadLevels(): Seq[Level] = {
    0 until 50 map { i => loadLevel(i) }
  }

  private def doPatchLevels(levels:Seq[Level], dryRun: Boolean): Unit = {
    var bank = 0xFF & romData(Rom.RoomDataBankPointer) // start with the same bank the Rom did b4
    // See the datacrystal explanation.  There are two sequential banks we have access to for level
    // data; so we can increment the bank number precisely once.
    var incBank = 0

    var bankOffset = 0
    var levelNumber = 0

    for (level <- levels) {
      val data = level.toByteBlob.toArray

      // patch in if this is not a test - this is the opposite of loadLevel()
      if (!dryRun) {
        require(bankOffset < Rom.MaxBytesPerBank)
        require(data.length < 0x100)

        // room data  is two bytes per level, LSB first
        romData(Rom.RoomDataOffsetPointer + levelNumber*2) = (0xFF & bankOffset).toByte
        romData(Rom.RoomDataOffsetPointer + levelNumber*2 + 1) = (0xFF & (bankOffset>>8)).toByte

        romData(Rom.RoomDataLengthPointer + levelNumber) = (data.length & 0xFF).toByte

        romData(Rom.RoomDataBankPointer + levelNumber) = (bank & 0xFF).toByte

        // write actual room data to bank
        val romLevelOffset = Rom.BaseRoomDataPointer + bank*0x1000 + bankOffset
        Array.copy(data, 0, romData, romLevelOffset, data.length)
      }

      // Update running offsets
      bankOffset += data.length
      if (bankOffset >= Rom.MaxBytesPerBank) {
        bankOffset = 0
        incBank += 1
        if (incBank > 1) {
          throw new IllegalArgumentException("Too many banks required to patch these levels")
        }
        bank += 1
      }
      levelNumber += 1
    }
  }

  def patchLevels(levels: Seq[Level]) {
    require(levels.length == 50, "There must be exactly 50 levels to patch in")

    // do a dryRun first to make sure we wouldn't corrupt the ROM by applying the patch
    try {
      doPatchLevels(levels, true)
    } catch {
      case ex: IllegalArgumentException => {
        throw new RuntimeException("This set of levels won't compress enough to fit!")
      }
    }

    // now do it for real
    doPatchLevels(levels, false)
  }
}
