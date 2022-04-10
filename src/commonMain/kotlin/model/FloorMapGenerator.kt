package model

// a class that generates a field without walls of a given size
class FloorMapGenerator(private val config: MapGeneratorConfig) : MapGenerator {
    override fun genMap(): Map {
        val field = List(config.height) { List(config.weight) { Tile(TileType.FLOOR) } }
        return Map(field)
    }
}
