package model

// action that transmits information about the movement of actors
data class ActorMoved(val actor: Actor, val position: Position) : Action
