package llt.common

import scala.collection.immutable.TreeSet

// TODO(jdowdell) - thumbnail image
sealed abstract class TileGroup (val guiOrder: Int,
                                 val name: String,
                                 val thumbnail: String)
    extends Ordered[TileGroup] {

  def compare(t: TileGroup): Int = guiOrder - t.guiOrder

}

// TODO(jdowdell) - confirm that no TileGroup or Tile have the same id
object TileGroup {
  case object VOID_SPACE_GROUP extends TileGroup(1, "Void", "TODO")
  case object WALLS_GROUP extends TileGroup(2, "Walls", "TODO")
  case object BOULDER_GROUP extends TileGroup(3, "Boulder", "TODO")
  case object BUSH_GROUP extends TileGroup(4, "Bush", "TODO")
  case object LAVA_GROUP extends TileGroup(5, "Lava", "TODO")

  case object DON_MEDUSA_GROUP extends TileGroup(20, "Don Medusa", "TODO")
  case object MEDUSA_GROUP extends TileGroup(21, "Medusa", "TODO")

  final val AllTileGroups: Set[TileGroup] = TreeSet(VOID_SPACE_GROUP, WALLS_GROUP, BOULDER_GROUP,
      BUSH_GROUP, LAVA_GROUP, DON_MEDUSA_GROUP, MEDUSA_GROUP
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
  case object DEFAULT_BLACK extends Tile(TileGroup.VOID_SPACE_GROUP, "DEFAULT BLACK",
      "TODO", "The Zero Tile, typically found outside the walls of the room", 0x0)

  // TODO(jdowdell) - Most of these are walls, some are "crystal", need to figure out
  // which and refactor the names.
  case object WALLS01 extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0x1)
  case object WALLS02 extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0x2)
  case object WALLS03 extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0x3)
  case object WALLS04 extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0x4)
  case object WALLS05 extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0x5)
  case object WALLS06 extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0x6)
  case object WALLS07 extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0x7)
  case object WALLS08 extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0x8)
  case object WALLS09 extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0x9)
  case object WALLS0A extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0xA)
  case object WALLS0B extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0xB)
  case object WALLS0C extends Tile(TileGroup.WALLS_GROUP, "TODO", "TODO", "WALLS (TODO)", 0xC)

  case object BOULDER extends Tile(TileGroup.BOULDER_GROUP, "Boulder",
      "TODO", "Blocks all attacks and movement; destroyed by hammer", 0xD)
  case object BUSH extends Tile(TileGroup.BUSH_GROUP, "Bush",
      "TODO", "Blocks no attacks, but blocks movement; invincible", 0xE)
  case object LAVA extends Tile(TileGroup.LAVA_GROUP, "Lava",
      "TODO", "Effectively same as water; but guaranteed no currents", 0xF)

  case object DON_MEDUSA_UP extends Tile(TileGroup.DON_MEDUSA_GROUP, "DON MEDUSA (UP)",
      "TODO", "Don Medusa initially traveling up", 0x90)
  case object DON_MEDUSA_RIGHT extends Tile(TileGroup.DON_MEDUSA_GROUP, "DON MEDUSA (RIGHT)",
      "TODO", "Don Medusa initially traveling right", 0x91)
  case object DON_MEDUSA_DOWN extends Tile(TileGroup.DON_MEDUSA_GROUP, "DON MEDUSA (DOWN)",
      "TODO", "Don Medusa initially traveling down", 0x92)
  case object DON_MEDUSA_LEFT extends Tile(TileGroup.DON_MEDUSA_GROUP, "DON MEDUSA (LEFT)",
      "TODO", "Don Medusa initially traveling left", 0x93)

  case object MEDUSA_UP extends Tile(TileGroup.MEDUSA_GROUP, "MEDUSA (UP)",
      "TODO", "Medusa (directionality ignored by game; all four are the same)", 0x94)
  case object MEDUSA_RIGHT extends Tile(TileGroup.MEDUSA_GROUP, "MEDUSA (RIGHT)",
      "TODO", "Medusa (directionality ignored by game; all four are the same)", 0x95)
  case object MEDUSA_DOWN extends Tile(TileGroup.MEDUSA_GROUP, "MEDUSA (DOWN)",
      "TODO", "Medusa (directionality ignored by game; all four are the same)", 0x96)
  case object MEDUSA_LEFT extends Tile(TileGroup.MEDUSA_GROUP, "MEDUSA (LEFT)",
      "TODO", "Medusa (directionality ignored by game; all four are the same)", 0x97)

  final val AllTiles: Set[Tile] = TreeSet(DON_MEDUSA_UP, DON_MEDUSA_RIGHT, DON_MEDUSA_DOWN,
      DON_MEDUSA_LEFT, MEDUSA_UP, MEDUSA_RIGHT, MEDUSA_DOWN, MEDUSA_LEFT
  )
}
