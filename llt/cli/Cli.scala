package llt.cli {
  object Cli {
    def main(args: Array[String]) {
      require(args.length >= 1, "No args in main()! Usage: scala llt.cli.Cli [romfile]")
      new llt.io.RomReader(args(0)).loadLevels
    }
  }
}
