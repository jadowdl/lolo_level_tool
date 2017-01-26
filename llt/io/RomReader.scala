package llt.io

import java.io.{ByteArrayOutputStream, File, FileInputStream, InputStream}
import java.security.MessageDigest

import llt.common.Level

class RomReader (romDataStream: java.io.InputStream) {
  def this(f: File) = this(new java.io.FileInputStream(f))
  def this(s: String) = this(new File(s))

  // Call whenever you're afraid you might be over the limit.
  def confirmFinalSizeLargerThan(fileSize: Int) {
    require(fileSize <= Rom.RomSize, "File too big to be a rom (" + fileSize + " bytes)")
  }

  // ala https://www.mkyong.com/java/java-sha-hashing-example/
  private def digest2HexStr(md: MessageDigest): String = {
    val bytes = md.digest
    val sb = new StringBuffer()
    md.digest.map( b => sb.append(Integer.toHexString( b & 0xFF )))
    sb.toString
  }

  def loadRom(): Rom = {
    val bytes = loadRomFromStream(romDataStream)
    new Rom(bytes)
  }

  def loadRomFromStream(inputStream : InputStream): Array[Byte] = {
    // Not guaranteed to be the filesize, but is at least a lower bound
    confirmFinalSizeLargerThan(inputStream.available)

    // Old school java-esque read-a-file.  Is there no better way in scala?
    val baos = new ByteArrayOutputStream(65536)
    val buffer = new Array[Byte](65536)
    val md = MessageDigest.getInstance("SHA-256")
    var totalRead = 0
    var amountRead = 0

    // TODO(jdowdell) - once in Java 7+ env, switch to java nio Files
    try {
      // Read the file, until its obvious we have the wrong one or we get all of it.
      // Update sha hash as we go.
      amountRead = inputStream.read(buffer)
      while (amountRead != -1 && totalRead <= Rom.RomSize) {
        totalRead += amountRead
        md.update(buffer, 0, amountRead)
        baos.write(buffer, 0, amountRead)
        amountRead = inputStream.read(buffer)
      }
      inputStream.close
    } catch {
      case e: Exception => println(e)
    }

    // Die on wrong file.
    // TODO(jdowdell) - guide user on common errors, like their .nes is actually a .zip with a .nes
    // inside it.
    val sha256 = digest2HexStr(md)
    require(totalRead == Rom.RomSize,
            "Filesize mismatch: read " + totalRead + " bytes; expected " + Rom.RomSize)
    require(sha256 == Rom.RomSha256, "SHA256 of first " + Rom.RomSize + " bytes = "
                                     + sha256 + ", expected " + Rom.RomSha256)

    // Return bytes read
    baos.toByteArray
  }
}
