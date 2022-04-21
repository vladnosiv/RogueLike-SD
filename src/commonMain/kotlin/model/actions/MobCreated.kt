package model.actions

import model.Actor
import model.Position

data class MobCreated(val position: Position, val actor: Actor): Action
