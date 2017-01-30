package llt.common

import scala.collection.mutable.{Queue, ListBuffer, Map}

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

      // In the rom you can rewrite a level and pad the end of a stream with junk; the game will
      // stop reading once it's filled up the room with tiles and ignore the extra stuff.  With
      // canonical level data though this doesn't happen, and I'd rather be too strict and catch
      // other mistakes than be lenient and try to do the right thing.
      if (arrayMultiIndex/COLUMNS >= ROWS) {
        throw new Exception("room pointer out of bounds - failed to decompress level data (you" +
                            "sure you got the right start and stop location?)")
      }

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

  def fromByteIterator(it: Iterator[Byte]): Level = {
    decompressTileStream(it.map( b => Tile.hexToTile( (0xFF & b) )))
  }

  def fromRomByteBlob(bytes: Seq[Byte]): Level = {
    // TODO - remove; but super useful for debugging for now
    // println(bytes.map("%02X" format _).mkString)
    fromByteIterator(bytes.iterator)
  }

  def fromRomHexDumpString(chars: String): Level = {
    fromByteIterator(chars.grouped(2).map(s => Integer.parseInt(s, 16).toByte))
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

  // Compares each column piecewise between row1 and row2 from i..j, unless row1==row2 in which
  // case we check that all tiles are the same (abuse of notation).  i..j is inclusive.
  private def tileStretchEqual(row1: Int, row2: Int, i: Int, j: Int): Boolean = {
    if (j < i) {
      false
    }
    else if (row1 == row2) {
      (tiles(row1).slice(i, j+1).reduce((a,b) => if(a==b) a else Tile.SENTINEL_TILE)
                               != Tile.SENTINEL_TILE)
    }
    else if (row2 < Level.ROWS) {
      tiles(row1).slice(i, j+1).sameElements(tiles(row2).slice(i, j+1))
    }
    else {
      // "ROW 16" is all black.
      (tiles(row1).slice(i, j+1).foldLeft(true)((accum, t) => accum && (t == Tile.DEFAULT_BLACK)))
    }
  }

  // Side effect - updates memoTable to have the entry for (i, j), columns inclusive on row `row`.
  // calculates all other entries needed recursively.
  private def compressionDynProg(row: Int, i: Int, j: Int,
                                 memoTable: Map[(Int, Int), ListBuffer[Tile]]): ListBuffer[Tile] = {
    require(j >= i)
    if (memoTable.contains((i, j))) {
      memoTable((i, j))
    } else {
      // Fill the memoTable with the result
      val result = (
        if (i==j) {
          ListBuffer[Tile](tiles(row)(i))
        } else if (tileStretchEqual(row, row+1, i, j)) {
          ListBuffer[Tile](Tile.hexToTile(0xEF + j-i))
        } else {
          val best = (i to (j-1)).map(k =>
            compressionDynProg(row, i, k, memoTable) ++ compressionDynProg(row, k+1, j, memoTable)
          ).reduce((a,b) => if(a.length < b.length) a else b)
          if (tileStretchEqual(row, row, i, j) && 3 <= best.length) {
            ListBuffer[Tile](Tile.TILE_META_REPEAT_SPECIAL, Tile.hexToTile(j-i-1), tiles(row)(i))
          } else {
            best
          }
        }
      )
      memoTable += (i,j) -> result
      result
    }
  }

  private def compressionDynProgEntry(row: Int) : ListBuffer[Tile] = {
    val memoTable: Map[(Int, Int), ListBuffer[Tile]] = Map()
    compressionDynProg(row, 0, Level.COLUMNS-1, memoTable)
  }

  def toCompressedTileStream() : Iterator[Tile] = {
    ((Level.ROWS-1) to 0 by -1).map(compressionDynProgEntry).reduce((a,b)=>a++b).iterator
  }

  def toByteBlob() : Iterator[Byte] = {
    toCompressedTileStream().map(a => a.hexCode.toByte)
  }

  // Convenience function, for debugging.  Prints out the rows REVERSED from the order they occur
  // in the ROM as, so that the hex dump looks like the room (top left corner of output is top left
  // corner of room).  Consider `.grouped(32).foreach(println)` to get it in a 15x16 ascii grid. If
  // you wanted to turn that around and pass it in for Level.fromRomHexDumpString you'd then need
  // to pass that 15x16 through the `tac` command and then join it back together as a single blob.
  def toDecompressedRowReversedHexDump() : String = {
    ((0 until Level.ROWS) flatMap {
      row => tiles(row).map {
        tile => "%02X" format tile.hexCode
      }
    }).mkString("")
  }
}
