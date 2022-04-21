package model

import model.actors.MainCharacterConfig
import model.map.FloorMapGenerator
import model.map.MapGeneratorConfig

class EnvironmentConfig(lvl: Int) {
    val generator = FloorMapGenerator(MapGeneratorConfig(32, 32))
    val mainCharacterConfig = MainCharacterConfig(Position(16, 16), 10, 0, Direction.RIGHT)
}
