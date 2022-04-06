package model

class Map(height: Int, weight: Int) {
    val field = List(height) { List(weight) { Tile(TileType.FLOOR) } }
}
