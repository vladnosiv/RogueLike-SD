package model.actors.mobs

import model.Position
import model.actors.mobs.strategies.StrategyType

data class MobConfig(
    val type: MobType,
    val position: Position,
    val hp: Int,
    val power: Int,
    val keepExp: Int,
    val strategy: StrategyType
)
