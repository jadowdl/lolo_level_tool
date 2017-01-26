package llt.cli {
  object Cli {
    def main(args: Array[String]) {
      require(args.length >= 2, "No args in main()! Usage: scala llt.cli.Cli [romfile] [newRomFile]")
      require(args(0) != args(1), "Can't overwrite original ROM; please change output filename")
      val rom = new llt.io.RomReader(args(0)).loadRom()
      val levels = rom.loadLevels()
      0
    }
  }
}
