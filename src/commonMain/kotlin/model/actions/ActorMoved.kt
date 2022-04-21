package model.actions

import model.Actor
import model.Position

// action that transmits information about the movement of actors
data class ActorMoved(val actor: Actor, val position: Position) : Action
