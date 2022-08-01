package com.hse.sd.roguelike.model.actors.mobs.states

import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actors.mobs.Mob

abstract class State(private val mob: Mob) {
    abstract val stateType: StateType

    abstract fun makeMove(): List<Action>
    abstract fun changeStateIfNeeded()
}
