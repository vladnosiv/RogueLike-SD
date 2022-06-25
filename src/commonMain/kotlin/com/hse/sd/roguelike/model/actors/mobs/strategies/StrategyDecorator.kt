package com.hse.sd.roguelike.model.actors.mobs.strategies

import com.hse.sd.roguelike.model.actors.mobs.Mob

interface StrategyDecorator: Strategy {
    val decorated: Strategy?

    fun getDecoratedAction(mob: Mob) = decorated!!.makeAct(mob)
}