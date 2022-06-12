package model.actions

import model.Direction
import model.actors.Actor

data class MobMoved(val actor: Actor, val dx: Int, val dy: Int, val direction: Direction) : Action
