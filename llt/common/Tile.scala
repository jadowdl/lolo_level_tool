package llt.common

import scala.collection.immutable.TreeSet

// TODO(jdowdell) - thumbnail image
sealed abstract class TileGroup (val guiOrder: Int,
                                 val name: String,
                                 val thumbnail: String)
    extends Ordered[TileGroup] {

  def compare(t: TileGroup): Int = guiOrder - t.guiOrder

}

object TileGroup {
  case object DON_MEDUSA_GROUP extends TileGroup(1, "Don Medusa", "TODO")
  case object MEDUSA_GROUP extends TileGroup(2, "Medusa", "TODO")

  final val AllTileGroups: Set[TileGroup] = TreeSet(DON_MEDUSA_GROUP, MEDUSA_GROUP
  )
}

// TODO(jdowdell) - thumbnail image
sealed abstract class Tile (val group: TileGroup,
                            val name: String,
                            val desc: String,
                            val thumbnail: String,
                            val hexCode: Int)
    extends Ordered[Tile] {

  def compare(t: Tile): Int = hexCode - t.hexCode

}

object Tile {
  case object DON_MEDUSA_UP extends Tile(DON_MEDUSA_GROUP, "DON MEDUSA (UP)",
      "TODO", "Don Medusa initially traveling up", 0x90)
  case object DON_MEDUSA_RIGHT extends Tile(DON_MEDUSA_GROUP, "DON MEDUSA (RIGHT)",
      "TODO", "Don Medusa initially traveling right", 0x91)
  case object DON_MEDUSA_DOWN extends Tile(DON_MEDUSA_GROUP, "DON MEDUSA (DOWN)",
      "TODO", "Don Medusa initially traveling down", 0x92)
  case object DON_MEDUSA_LEFT extends Tile(DON_MEDUSA_GROUP, "DON MEDUSA (LEFT)",
      "TODO", "Don Medusa initially traveling left", 0x93)

  case object MEDUSA_UP extends Tile(MEDUSA_GROUP, "MEDUSA (UP)",
      "TODO", "Medusa (directionality ignored by game; all four are the same)", 0x94)
  case object MEDUSA_RIGHT extends Tile(MEDUSA_GROUP, "MEDUSA (RIGHT)",
      "TODO", "Medusa (directionality ignored by game; all four are the same)", 0x95)
  case object MEDUSA_DOWN extends Tile(MEDUSA_GROUP, "MEDUSA (DOWN)",
      "TODO", "Medusa (directionality ignored by game; all four are the same)", 0x96)
  case object MEDUSA_LEFT extends Tile(MEDUSA_GROUP, "MEDUSA (LEFT)",
      "TODO", "Medusa (directionality ignored by game; all four are the same)", 0x97)

  final val AllTiles: Set[Tile] = TreeSet(DON_MEDUSA_UP, DON_MEDUSA_RIGHT, DON_MEDUSA_DOWN,
      DON_MEDUSA_LEFT, MEDUSA_UP, MEDUSA_RIGHT, MEDUSA_DOWN, MEDUSA_LEFT
  )
}
