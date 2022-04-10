package view

import com.soywiz.korge.view.SpriteAnimation

/**
 * Обертка для хранения тайла с позицией на карте и спрайтом
 */
data class Tile(val x: Int, val y: Int, val spriteAnimation: SpriteAnimation)