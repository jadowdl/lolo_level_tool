import java.io.{File, FileInputStream, InputStream}

package llt.io {
  class RomReader (romDataStream: java.io.InputStream) {
    def this(f: File) = this(new java.io.FileInputStream(f))
    def this(s: String) = this(new File(s))

    def loadLevels(): Int = {
      val fileSize = romDataStream.available
      require(fileSize < 1000000000, "File too big to be a rom (" + fileSize + " bytes)")
      val bytes = new Array[Byte](romDataStream.available)
      try {
        // TODO(jdowdell) - once in Java 7+ env, switch to java nio Files
        romDataStream.read(bytes)
        romDataStream.close()
        // TODO(jdowdell) - check MD5 Against magic string
      } catch {
        case e: Exception => println(e)
      }

      // TODO(jdowdell) - change return type to Level
      println("First byte: " + bytes(0))
      return 0
    }
  }
}
