package com.hse.sd.roguelike.view.sprites

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.SpriteAnimation

data class CharacterSprite(
    val idle: SpriteAnimation,
    val run: SpriteAnimation,
    val idleDuration: TimeSpan = 100.milliseconds,
    val runDuration: TimeSpan = 60.milliseconds,
    val getPosition: (x: Int, y: Int) -> Pair<Int, Int> = { x: Int, y: Int -> 16 * x + 8 to 16 * (y + 1) }
)