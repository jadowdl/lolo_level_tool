package llt.common

object Level {
  def fromRomByteBlob(bytes: Array[Byte]): Level = {
    // println("DBG Level: " + bytes.map(b => Integer.toHexString(0xFF&b)).mkString(""))
    new Level()
  }
}

class Level {
  var level = 0

  def setLevelNumber(level: Int): Level = {
    this.level = level
    this
  }
}
