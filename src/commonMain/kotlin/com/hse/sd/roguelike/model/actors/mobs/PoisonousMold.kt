package com.hse.sd.roguelike.model.actors.mobs

import com.hse.sd.roguelike.model.Position
import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actors.mobs.strategies.Strategy
import kotlin.random.Random

class PoisonousMold(
    position: Position,
    hp: Int,
    power: Int,
    keepExp: Int,
    strategy: Strategy,
    private val probability: Double
) :
    Mob(position, hp, power, keepExp, strategy), Replicated {
    override val type: MobType
        get() = MobType.POISONOUS_MOLD

    override fun replicate(): PoisonousMold {
        val newStrategy = strategy.type.build(strategy.environment)
        return PoisonousMold(position, hp, power, keepExp, newStrategy, probability / 2)
    }

    override fun makeTypeSpecificAction(): List<Action> {
        val number = Random.nextDouble(0.0, 1.0)
        val map = strategy.environment.map

        return if (number < probability) {
            val actions = mutableListOf<Action>()
            for (direction in com.hse.sd.roguelike.model.Direction.values()) {
                if(map.canStep(position + direction)) {
                    val newMob = this.replicate()
                    newMob.position = position + direction
                    actions.addAll(map.createMob(position + direction, newMob))
                }
            }
            return actions
        } else {
            emptyList()
        }
    }
}
