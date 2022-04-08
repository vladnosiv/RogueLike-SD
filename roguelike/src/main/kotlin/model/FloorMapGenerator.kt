package model

class FloorMapGenerator : MapGenerator {
    override fun genMap(config: MapGeneratorConfig): Map {
        val field = List(config.height) { List(config.weight) { Tile(TileType.FLOOR) } }
        return Map(field)
    }
}
