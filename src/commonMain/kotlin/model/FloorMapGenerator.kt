package model

class FloorMapGenerator(private val config: MapGeneratorConfig) : MapGenerator {
    override fun genMap(): Map {
        val field = List(config.height) { List(config.weight) { Tile(TileType.FLOOR) } }
        return Map(field)
    }
}
