package llt.io

import llt.common.Level

object Rom {
  // I don't know what's up with the ROM I have access to, but it's longer and has a different
  // sha256 than the ones mentioned on datacrystal.
  final val RomSize = 262160
  final val RomSha256 = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"
  final val LevelCount = 50  // lolo1 has 50 levels exactly

  // Magic Numbers from http://datacrystal.romhacking.net/wiki/Adventures_of_Lolo:ROM_map
  // These are all byte offsets from the beginning of the ROM (so I call them "Pointer")
  final val RoomDataOffsetPointer = 0x3A6A
  final val RoomDataLengthPointer = 0x3ACE
  final val RoomDataBankPointer = 0x3B00

  // Also, in my dump, the room data is much further in than what datacrystal mentions.
  final val BaseRoomDataPointer = 0x20010
}

class Rom (romData: Array[Byte]){

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

  def loadLevels(): Any = {
    val levels = (0 until 50).map { i => loadLevel(i) }
    levels
  }

  private def doPatchLevels(levels:Seq[Level], dryRun: Boolean) {

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
