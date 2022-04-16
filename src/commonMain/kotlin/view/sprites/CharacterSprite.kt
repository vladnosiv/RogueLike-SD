package view.sprites

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import com.soywiz.korge.view.SpriteAnimation

data class CharacterSprite(
    val idle: SpriteAnimation,
    val run: SpriteAnimation,
    val idleDuration: TimeSpan = 100.milliseconds,
    val runDuration: TimeSpan = 60.milliseconds
)