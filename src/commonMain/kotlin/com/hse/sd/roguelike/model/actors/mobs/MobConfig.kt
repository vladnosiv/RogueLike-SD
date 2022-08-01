package com.hse.sd.roguelike.model.actors.mobs

import com.hse.sd.roguelike.model.Position
import com.hse.sd.roguelike.model.actors.mobs.strategies.StrategyType

data class MobConfig(
    val type: MobType,
    val position: Position,
    val hp: Int,
    val power: Int,
    val keepExp: Int,
    val strategy: StrategyType
)
