package model

import model.actors.MainCharacterConfig
import model.actors.Mob
import model.actors.MobConfig
import model.actors.PassStrategy
import model.map.FloorMapGenerator
import model.map.MapGeneratorConfig

class EnvironmentConfig(lvl: Int) {
    val generator = FloorMapGenerator(MapGeneratorConfig(32, 32))
    val mainCharacterConfig = MainCharacterConfig(Position(16, 16), 10, 0, Direction.RIGHT)
    fun getMobs(environment: Environment): List<MobConfig>{
        return listOf(
            MobConfig(Position(10, 10), 1, PassStrategy(environment)),
            MobConfig(Position(20, 20), 1, PassStrategy(environment))
        )
    }
}
