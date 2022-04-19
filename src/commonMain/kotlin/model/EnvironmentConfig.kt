package model

class EnvironmentConfig(lvl: Int) {
    val generator = FloorMapGenerator(MapGeneratorConfig(32, 32))
    val mainCharacterConfig = MainCharacterConfig(Position(16, 16), 100, 0)
}
