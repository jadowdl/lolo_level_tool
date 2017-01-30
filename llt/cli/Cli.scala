package llt.cli

import java.io.{File, FileOutputStream}

import llt.common.Level.fromRomHexDumpString

object Cli {
  def main(args: Array[String]) : Unit = {
    require(args.length >= 2, "No args in main()! Usage: scala llt.cli.Cli [romfile] [newRomFile]")
    require(args(0) != args(1), "Can't overwrite original ROM; please change output filename")
    val rom = new llt.io.RomReader(args(0)).loadRom()
    val levels = rom.loadLevels().toArray


    // TODO - hack; for now, due to time constraints (demo in 2 days!) we're just manually patching
    // in some levels I created in Construction mode of "Souzouhe no Tabidachi"

    // New Level 1 - Fireshooter Cross
    levels(11)=fromRomHexDumpString("C1E0C1E0C1E0000000000000E3000000001819191919191919191919191A0000001B4D40400D40D440404040941B0000001B404040400D4040404040401B0000001B404B40404040489C4040401B0000001B400E0E0E0E0E0E4A4040401B0000001B4048404D40400E400D0D471B0000001B40488C408D400E4042424C1B0000001B404D4094404D0E400D0D491B0000001B40408F408E400E404040401B0000001B9040404040400E4040400E1B0000001B400E404D40404040400E0E1B0000001B40400E4040C040400E0E0E1B00000014202116161616161616162217000000111212121212121212121212130000")

    // New Level 4 - "Myrnas Tutorial"
    // (It was supposed to be a quick reminder of all aspects of the game, but was in fact a hard level
    // and inappropriate to be the first one)
    levels(13) = fromRomHexDumpString("C2E2C4E4C3E6000000000000E0000000001819191919191919191919191A0000001BC04D0E4D3E404440409B9B1B0000001B9C0E88413E433E41410E941B0000001B490E404142424B404040401B0000001B404D0E490E8E8E40474A0E1B0000001B9C0E3C41400E41410D0E4C1B0000001B3B3B3C0E820D428B4042401B0000001B0D0D0D8C41403E3E3E0E401B0000001B99484C414B4040404A400E1B0000001B0D0D0D4B404C40404A40401B0000001B8D4C4040400D40400D40911B0000001B8540404040D43E3E3E400E1B00000014151616161616161620212217000000111212121212121212121212130000")

    // "Water Adventure"
    levels(12) = fromRomHexDumpString("C1E0C1E0C1E0000000000000E0000000001819191919191919191919191A0000001B3B3B3B3B3BC0413D3D414D1B0000001B3C3D3D3D419C414D3C9C4D1B0000001B3E3E3E3E3E3B3B3E3E3E3A1B0000001B4D413D3D3D3D3D3D3D3D3D1B0000001B419C3B3B3B3B3B3B3B3B3C1B0000001B3E3A3E0D3E3E3E40404D3E1B0000001B3E3A3E0D0E3E3E409C403E1B0000001B3E3A3E0D0D0D3E8A40403E1B0000001B3D3D3E3E3E3E3E3E3E3E3E1B0000001B419C3E3B3B3B3B3B3B3A411B0000001B41413B3C41D4413D3D3D4C1B00000014151616162021161616162217000000111212121212121212121212130000")

    // "Medusa Island"
    levels(10) = fromRomHexDumpString("C1E5C1E0C1E0F4E0F10018FF09191AF0001BFF093A1BF0F24040403A4C3A3A4040F1F30E4040484B3A9C40F1F14040404B40403A3A409CF1F040403A3A43FF023A9C40F1F0C00D3A4C404094404D404DF1F3D440400EFF0240F1F1403A3A3AF33AF1F0FF05400E0EF3F03A3A0E0E404BFF0240F2F44040403A3A403AF1001415FF051620212217F00011FF091213F0")






    // Write to result ROM file
    // TODO(jdowdell) - once there's a gui, we probably don't need to write to File like this.
    rom.patchLevels(levels)
    val fos = new FileOutputStream(new File(args(1)))
    fos.write(rom.getRomData)
    fos.close()
  }
}
