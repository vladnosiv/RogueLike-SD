package com.hse.sd.roguelike.model.actions

import com.hse.sd.roguelike.model.Direction
import com.hse.sd.roguelike.model.actors.Actor

data class MobMoved(val actor: Actor, val dx: Int, val dy: Int, val direction: Direction) : Action
