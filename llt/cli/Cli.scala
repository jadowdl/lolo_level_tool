package llt.cli

import java.io.{File, FileOutputStream}

object Cli {
  def main(args: Array[String]) : Unit = {
    require(args.length >= 2, "No args in main()! Usage: scala llt.cli.Cli [romfile] [newRomFile]")
    require(args(0) != args(1), "Can't overwrite original ROM; please change output filename")
    val rom = new llt.io.RomReader(args(0)).loadRom()
    val levels = rom.loadLevels()


    // TODO - edit levels in some way...


    // Write to result ROM file
    // TODO(jdowdell) - once there's a gui, we probably don't need to write to File like this.
    rom.patchLevels(levels)
    val fos = new FileOutputStream(new File(args(1)))
    fos.write(rom.getRomData)
    fos.close()
  }
}
