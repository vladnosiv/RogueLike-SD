package model.actors

import model.Direction
import model.Position

// config setting the starting parameters of the main character's spawn
data class MainCharacterConfig(
    val position: Position,
    val hp: Int,
    val power: Int,
    val exp: Int,
    val direction: Direction
)
