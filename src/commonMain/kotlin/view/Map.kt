package view

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.sprite


class Map(container: Container, width: Int, height: Int) {
    init {
        val tiles = generateMap(width, height)
        tiles.forEach {
            it.forEach { tile ->
                container
                    .sprite(tile.spriteAnimation)
                    .position(16 * tile.x, 16 * tile.y)
                    .playAnimation()
            }
        }
    }

    /**
     * TODO: Change generation in HW-3
     */
    private fun generateMap(width: Int, height: Int): List<List<Tile>> {
        return List(width) { x -> List(height) { y -> when {
            x == 0 || x == width - 1 || y == 0 || y == height - 1 -> Tile(x, y, TileAnimation.Map.wall)
            else -> Tile(x, y, TileAnimation.Map.floor)
        } } }
    }
}