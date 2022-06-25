package com.hse.sd.roguelike.model.actors.mobs.states

import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actors.mobs.Cowardly
import com.hse.sd.roguelike.model.actors.mobs.Mob

class NormalState(private val mob: Mob): State(mob) {
    override val stateType = StateType.NORMAL

    override fun makeMove(): List<Action> {
        return mob.strategy.makeAct(mob)
    }

    override fun changeStateIfNeeded() {
        if (mob is Cowardly && mob.hp < mob.initialHp / 2) {
            mob.state = CowardlyState(mob)
        }
    }
}