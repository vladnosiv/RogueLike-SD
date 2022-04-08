package model

interface MapGenerator {
    fun genMap(config: MapGeneratorConfig): Map
}
