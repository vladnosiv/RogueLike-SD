package model

// config setting the starting parameters of the main character's spawn
data class MainCharacterConfig(val position: Position, val hp: Int, val exp: Int, val direction: Direction)
