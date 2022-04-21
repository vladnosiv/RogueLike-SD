package model.actions

import model.Direction
import model.actors.Actor

data class MobAttacked(val actor: Actor, val direction: Direction): Action
