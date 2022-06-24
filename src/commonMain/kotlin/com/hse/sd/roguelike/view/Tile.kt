package com.hse.sd.roguelike.view

/**
 * Обертка для хранения тайла с позицией на карте и спрайтом
 */
data class Tile(val type: TileType, val x: Int, val y: Int)