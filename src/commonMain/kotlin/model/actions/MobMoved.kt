package model.actions

import model.actors.Actor

data class MobMoved(val actor: Actor, val dx: Int, val dy: Int): Action
