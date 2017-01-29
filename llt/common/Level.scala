package llt.common

import scala.collection.mutable.Queue
import scala.collection.mutable.ListBuffer

// import llt.common.{Tile, TileGroup}

object Level {
  final val ROWS = 15
  final val COLUMNS = 16
  final val WRONG_DIMS_ERROR_MSG = ("Level() requires a " + Level.ROWS + "x" + Level.COLUMNS +
                                    " 2D array of Tiles in constructor")

  // Note - if you don't use META tiles in your stream, then decompress really just reads 256 tiles
  // into a 2D array and inits the level from it.
  def decompressTileStream(stream: Iterator[Tile]): Level = {
    // I don't know in scala how to easily move back and forth between interpretations of
    // a collections as multidimensional versus 1D.  The stream will fill bottom to top major
    // left to right minor.
    //
    // Also - gross - my scala version is too low to support Array.ofDim
    val levelData: Array[Array[Tile]] = Array.ofDim[Tile](ROWS, COLUMNS)
    var arrayMultiIndex = 0

    // As in the ROM, the "16th row" is imagined to be all 0s.
    val lastRowQueue: Queue[Tile] = Queue(List.fill(COLUMNS)(Tile.DEFAULT_BLACK): _*)

    def addTile(tile: Tile) {
      val lastRowTile = lastRowQueue.dequeue
      val toAdd = if (tile.group == TileGroup.META_GROUP) lastRowTile else tile

      levelData(ROWS-1 - arrayMultiIndex/COLUMNS)(arrayMultiIndex%COLUMNS) = toAdd
      lastRowQueue += toAdd
      arrayMultiIndex += 1
    }

    while(stream.hasNext) {
      val tile = stream.next
      if (tile == Tile.TILE_META_REPEAT_SPECIAL) {
        // Next two "tiles" are actually tile to repeat, and count of times to repeat it, -2
        val repeatCount = stream.next().hexCode + 2
        val actualTile = stream.next()
        0.to(repeatCount-1).foreach( i => addTile(actualTile) )
      } else if (tile.group == TileGroup.META_GROUP) {
        val repeatCount = tile.hexCode - 0xEF + 1  // agrees with ROM code
        0.to(repeatCount-1).foreach( i => addTile(tile) )
      } else {
        addTile(tile)
      }
    }
    new Level(levelData)
  }

  def fromRomByteBlob(bytes: Array[Byte]): Level = {
    // println("DBG Level: " + bytes.map(b => Integer.toHexString(0xFF&b)).mkString(""))
    decompressTileStream(bytes.iterator.map( b => Tile.hexToTile( (0xFF & b) )))
  }
}

/**
 * tilesToCopy - should be of dimension ROWSxCOLUMNS.  Will be copied.
 */
class Level (tilesToCopy: Array[Array[Tile]]) {
  require(tilesToCopy.length == Level.ROWS, Level.WRONG_DIMS_ERROR_MSG)
  for (i <- 0.to(Level.ROWS-1)) {
    require(tilesToCopy(i).length == Level.COLUMNS, Level.WRONG_DIMS_ERROR_MSG)
  }

  val tiles = tilesToCopy.map(_.clone)
  var level = 0

  def setLevelNumber(level: Int): Level = {
    this.level = level
    this
  }
}
