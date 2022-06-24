package com.hse.sd.roguelike.model.actors

import com.hse.sd.roguelike.model.Position

// config setting the starting parameters of the main character's spawn
data class MainCharacterConfig(
    val position: Position,
    val hp: Int,
    val power: Int,
    val exp: Int,
    val direction: com.hse.sd.roguelike.model.Direction
)
