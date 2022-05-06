package model

import model.actors.*
import model.items.Item
import model.items.Sword
import model.map.FloorMapGenerator
import model.map.MapGeneratorConfig

class EnvironmentConfig(lvl: Int) {
    val generator = FloorMapGenerator(MapGeneratorConfig(32, 32))
    val mainCharacterConfig = MainCharacterConfig(Position(16, 16), 10, 1, 0, Direction.RIGHT)
    val items = listOf<Pair<Position, Item>>( //TODO(check positions)
        Pair(Position(15, 15), Sword())
    )

    fun getMobs(environment: Environment): List<MobConfig> {
        return listOf(
            MobConfig(Position(10, 10), 100, 0, 1, PassStrategy(environment)),
            MobConfig(Position(20, 20), 1, 0, 1, AggressiveStrategy(environment)),
            MobConfig(Position(25, 25), 1, 0, 1, FearfulStrategy(environment))
        )
    }
}
