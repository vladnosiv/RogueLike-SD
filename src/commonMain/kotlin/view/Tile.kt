package view

import com.soywiz.korge.view.SpriteAnimation

/**
 * Обертка для хранения тайла с позицией на карте и спрайтом
 */
data class Tile(val type: TileType, val x: Int, val y: Int)