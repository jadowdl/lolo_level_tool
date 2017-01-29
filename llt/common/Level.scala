package llt.common

import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer

// import llt.common.{Tile, TileGroup}

object Level {
  // Note - if you don't use META tiles in your stream, then decompress really just reads 256 tiles
  // into a 2D array and inits the level from it.
  def decompressTileStream(stream: Iterator[Tile]): Level = {
    // I don't know in scala how to easily move back and forth between interpretations of
    // a collections as multidimensional versus 1D.  The stream will fill bottom to top major
    // left to right minor.
    //
    // Also - gross - my scala version is too low to support Array.ofDim
    val levelData: Array[Array[Tile]] = Array.ofDim[Tile](16, 16)
    var arrayMultiIndex = 0

    // As in the ROM, the "17th row" is imagined to be all 0s.
    val lastRowQueue: Queue[Tile] = Queue(List.fill(16)(Tile.DEFAULT_BLACK): _*)

    def addTile(tile: Tile) {
      val lastRowTile = lastRowQueue.dequeue
      val toAdd = if (tile.group == TileGroup.META_GROUP) lastRowTile else tile

      println("DEBUG: "+(arrayMultiIndex/16) + " " + (arrayMultiIndex%16) + " " + toAdd)
      levelData(15 - (arrayMultiIndex/16))(arrayMultiIndex%16) = toAdd
      lastRowQueue += toAdd
      arrayMultiIndex += 1
    }

    println("LOAD LEVEL")
    while(stream.hasNext) {
      val tile = stream.next
      if (tile == Tile.TILE_META_REPEAT_SPECIAL) {
        // Next two "tiles" are actually tile to repeat, and count of times to repeat it, -2
        val repeatCount = stream.next().hexCode + 2
        val actualTile = stream.next()
        println("  META REPEAT SPECIAL: " + actualTile + " " + repeatCount)
        0.to(repeatCount-1).foreach( i => addTile(actualTile) )
      } else if (tile.group == TileGroup.META_GROUP) {
        val repeatCount = tile.hexCode - 0xEF + 1  // agrees with ROM code
        println("  META REPEAT : " + tile + " " + repeatCount)
        0.to(repeatCount-1).foreach( i => addTile(tile) )
      } else {
        println("  PLAIN ADD: " + tile)
        addTile(tile)
      }
    }
    println("DEBUG: arrayMultiIndex was: "+ arrayMultiIndex)
    new Level(levelData)
  }

  def fromRomByteBlob(bytes: Array[Byte]): Level = {
    // println("DBG Level: " + bytes.map(b => Integer.toHexString(0xFF&b)).mkString(""))
    decompressTileStream(bytes.iterator.map( b => Tile.hexToTile( (0xFF & b) )))
  }
}

/**
 * tilesToCopy - should be of dimension 16x16.  Will be copied.
 */
class Level (tilesToCopy: Array[Array[Tile]]) {
  require(tilesToCopy.length == 16, "Level() requires a 16x16 2D array of Tiles in constructor")
  for (i <- 0.to(15)) {
    require(tilesToCopy(i).length == 16,
            "Level() requires a 16x16 2D array of Tiles in constructor")
  }

  val tiles = tilesToCopy.map(_.clone)
  var level = 0

  def setLevelNumber(level: Int): Level = {
    this.level = level
    this
  }
}
