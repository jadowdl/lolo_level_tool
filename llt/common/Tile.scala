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

  // See bogus tiles section below for explanation.
  case object TODO_GROUP extends TileGroup(6, "Todo - Delete", "TODO")

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

  // Bogus Tiles Section.  This is very tedious to do all 256 tiles and will take a long time
  // to code up.  The majority of the the use case involves the GUI, which isn't being written
  // up front.  So I'm creating some place holder enums for now, and will refactor when I have
  // more time later.

  case object TILE10 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x10)
  case object TILE11 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x11)
  case object TILE12 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x12)
  case object TILE13 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x13)
  case object TILE14 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x14)
  case object TILE15 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x15)
  case object TILE16 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x16)
  case object TILE17 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x17)
  case object TILE18 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x18)
  case object TILE19 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x19)
  case object TILE1A extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x1A)
  case object TILE1B extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x1B)
  case object TILE1C extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x1C)
  case object TILE1D extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x1D)
  case object TILE1E extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x1E)
  case object TILE1F extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x1F)
  case object TILE20 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x20)
  case object TILE21 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x21)
  case object TILE22 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x22)
  case object TILE23 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x23)
  case object TILE24 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x24)
  case object TILE25 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x25)
  case object TILE26 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x26)
  case object TILE27 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x27)
  case object TILE28 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x28)
  case object TILE29 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x29)
  case object TILE2A extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x2A)
  case object TILE2B extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x2B)
  case object TILE2C extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x2C)
  case object TILE2D extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x2D)
  case object TILE2E extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x2E)
  case object TILE2F extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x2F)
  case object TILE30 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x30)
  case object TILE31 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x31)
  case object TILE32 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x32)
  case object TILE33 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x33)
  case object TILE34 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x34)
  case object TILE35 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x35)
  case object TILE36 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x36)
  case object TILE37 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x37)
  case object TILE38 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x38)
  case object TILE39 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x39)
  case object TILE3A extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x3A)
  case object TILE3B extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x3B)
  case object TILE3C extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x3C)
  case object TILE3D extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x3D)
  case object TILE3E extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x3E)
  case object TILE3F extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x3F)
  case object TILE40 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x40)
  case object TILE41 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x41)
  case object TILE42 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x42)
  case object TILE43 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x43)
  case object TILE44 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x44)
  case object TILE45 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x45)
  case object TILE46 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x46)
  case object TILE47 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x47)
  case object TILE48 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x48)
  case object TILE49 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x49)
  case object TILE4A extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x4A)
  case object TILE4B extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x4B)
  case object TILE4C extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x4C)
  case object TILE4D extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x4D)
  case object TILE4E extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x4E)
  case object TILE4F extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x4F)
  case object TILE50 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x50)
  case object TILE51 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x51)
  case object TILE52 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x52)
  case object TILE53 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x53)
  case object TILE54 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x54)
  case object TILE55 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x55)
  case object TILE56 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x56)
  case object TILE57 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x57)
  case object TILE58 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x58)
  case object TILE59 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x59)
  case object TILE5A extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x5A)
  case object TILE5B extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x5B)
  case object TILE5C extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x5C)
  case object TILE5D extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x5D)
  case object TILE5E extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x5E)
  case object TILE5F extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x5F)
  case object TILE60 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x60)
  case object TILE61 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x61)
  case object TILE62 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x62)
  case object TILE63 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x63)
  case object TILE64 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x64)
  case object TILE65 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x65)
  case object TILE66 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x66)
  case object TILE67 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x67)
  case object TILE68 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x68)
  case object TILE69 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x69)
  case object TILE6A extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x6A)
  case object TILE6B extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x6B)
  case object TILE6C extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x6C)
  case object TILE6D extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x6D)
  case object TILE6E extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x6E)
  case object TILE6F extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x6F)
  case object TILE70 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x70)
  case object TILE71 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x71)
  case object TILE72 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x72)
  case object TILE73 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x73)
  case object TILE74 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x74)
  case object TILE75 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x75)
  case object TILE76 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x76)
  case object TILE77 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x77)
  case object TILE78 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x78)
  case object TILE79 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x79)
  case object TILE7A extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x7A)
  case object TILE7B extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x7B)
  case object TILE7C extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x7C)
  case object TILE7D extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x7D)
  case object TILE7E extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x7E)
  case object TILE7F extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x7F)
  case object TILE80 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x80)
  case object TILE81 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x81)
  case object TILE82 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x82)
  case object TILE83 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x83)
  case object TILE84 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x84)
  case object TILE85 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x85)
  case object TILE86 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x86)
  case object TILE87 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x87)
  case object TILE88 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x88)
  case object TILE89 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x89)
  case object TILE8A extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x8A)
  case object TILE8B extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x8B)
  case object TILE8C extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x8C)
  case object TILE8D extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x8D)
  case object TILE8E extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x8E)
  case object TILE8F extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x8F)
  case object TILE90 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x90)







  case object TILE98 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x98)
  case object TILE99 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x99)
  case object TILE9A extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x9A)
  case object TILE9B extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x9B)
  case object TILE9C extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x9C)
  case object TILE9D extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x9D)
  case object TILE9E extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x9E)
  case object TILE9F extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0x9F)
  case object TILEA0 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xA0)
  case object TILEA1 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xA1)
  case object TILEA2 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xA2)
  case object TILEA3 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xA3)
  case object TILEA4 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xA4)
  case object TILEA5 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xA5)
  case object TILEA6 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xA6)
  case object TILEA7 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xA7)
  case object TILEA8 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xA8)
  case object TILEA9 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xA9)
  case object TILEAA extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xAA)
  case object TILEAB extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xAB)
  case object TILEAC extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xAC)
  case object TILEAD extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xAD)
  case object TILEAE extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xAE)
  case object TILEAF extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xAF)
  case object TILEB0 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xB0)
  case object TILEB1 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xB1)
  case object TILEB2 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xB2)
  case object TILEB3 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xB3)
  case object TILEB4 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xB4)
  case object TILEB5 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xB5)
  case object TILEB6 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xB6)
  case object TILEB7 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xB7)
  case object TILEB8 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xB8)
  case object TILEB9 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xB9)
  case object TILEBA extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xBA)
  case object TILEBB extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xBB)
  case object TILEBC extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xBC)
  case object TILEBD extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xBD)
  case object TILEBE extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xBE)
  case object TILEBF extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xBF)
  case object TILEC0 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xC0)
  case object TILEC1 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xC1)
  case object TILEC2 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xC2)
  case object TILEC3 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xC3)
  case object TILEC4 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xC4)
  case object TILEC5 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xC5)
  case object TILEC6 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xC6)
  case object TILEC7 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xC7)
  case object TILEC8 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xC8)
  case object TILEC9 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xC9)
  case object TILECA extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xCA)
  case object TILECB extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xCB)
  case object TILECC extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xCC)
  case object TILECD extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xCD)
  case object TILECE extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xCE)
  case object TILECF extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xCF)
  case object TILED0 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xD0)
  case object TILED1 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xD1)
  case object TILED2 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xD2)
  case object TILED3 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xD3)
  case object TILED4 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xD4)
  case object TILED5 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xD5)
  case object TILED6 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xD6)
  case object TILED7 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xD7)
  case object TILED8 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xD8)
  case object TILED9 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xD9)
  case object TILEDA extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xDA)
  case object TILEDB extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xDB)
  case object TILEDC extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xDC)
  case object TILEDD extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xDD)
  case object TILEDE extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xDE)
  case object TILEDF extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xDF)
  case object TILEE0 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xE0)
  case object TILEE1 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xE1)
  case object TILEE2 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xE2)
  case object TILEE3 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xE3)
  case object TILEE4 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xE4)
  case object TILEE5 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xE5)
  case object TILEE6 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xE6)
  case object TILEE7 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xE7)
  case object TILEE8 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xE8)
  case object TILEE9 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xE9)
  case object TILEEA extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xEA)
  case object TILEEB extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xEB)
  case object TILEEC extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xEC)
  case object TILEED extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xED)
  case object TILEEE extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xEE)
  case object TILEEF extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xEF)
  case object TILEF0 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xF0)
  case object TILEF1 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xF1)
  case object TILEF2 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xF2)
  case object TILEF3 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xF3)
  case object TILEF4 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xF4)
  case object TILEF5 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xF5)
  case object TILEF6 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xF6)
  case object TILEF7 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xF7)
  case object TILEF8 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xF8)
  case object TILEF9 extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xF9)
  case object TILEFA extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xFA)
  case object TILEFB extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xFB)
  case object TILEFC extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xFC)
  case object TILEFD extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xFD)
  case object TILEFE extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xFE)
  case object TILEFF extends Tile(TileGroup.TODO_GROUP, "TODO", "TODO", "TODO", 0xFF)

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
