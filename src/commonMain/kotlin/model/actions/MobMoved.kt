package model.actions

import model.Actor

data class MobMoved(val actor: Actor, val dx: Int, val dy: Int): Action
