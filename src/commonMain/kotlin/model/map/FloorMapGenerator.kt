package model.map

// a class that generates a field without walls of a given size
class FloorMapGenerator(private val config: MapGeneratorConfig) : MapGenerator {
    override fun genMap(): Map {
        val height = config.height
        val width = config.width
        return Map(List(width) { x -> List(height) { y -> when {
            x == 0 || x == width - 1 || y == 0 || y == height - 1 -> Tile(TileType.WALL, x, y)
            else -> Tile(TileType.FLOOR, x, y)
        }}})
    }
}
