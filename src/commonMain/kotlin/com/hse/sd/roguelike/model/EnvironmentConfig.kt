package com.hse.sd.roguelike.model

import com.hse.sd.roguelike.model.actors.MainCharacterConfig
import com.hse.sd.roguelike.model.actors.mobs.MobConfig
import com.hse.sd.roguelike.model.actors.mobs.MobType
import com.hse.sd.roguelike.model.actors.mobs.strategies.StrategyType
import com.hse.sd.roguelike.model.items.Item
import com.hse.sd.roguelike.model.items.Sword
import com.hse.sd.roguelike.model.map.FloorMapGenerator
import com.hse.sd.roguelike.model.map.MapGeneratorConfig

class EnvironmentConfig(lvl: Int) {
    val generator = FloorMapGenerator(MapGeneratorConfig(32, 32))
    val mainCharacterConfig = MainCharacterConfig(Position(16, 16), 10, 1, 0, Direction.RIGHT)
    val items = listOf<Pair<Position, Item>>( //TODO(check positions)
        Pair(Position(15, 15), Sword())
    )
    val mobConfigs = listOf(
        MobConfig(MobType.ZOMBIE, Position(10, 10), 100, 0, 1, StrategyType.PASSIVE),
        MobConfig(MobType.SKELETON, Position(20, 20), 1, 0, 1, StrategyType.AGGRESSIVE),
        MobConfig(MobType.BIG_ZOMBIE, Position(25, 25), 1, 0, 1, StrategyType.FEARFUL),

        MobConfig(MobType.POISONOUS_MOLD, Position(22, 22), 1, 0, 1, StrategyType.PASSIVE),
    )
}
