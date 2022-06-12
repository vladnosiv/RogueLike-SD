package view

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korge.view.sprite


/**
 * Класс карты
 * Генерирует простую карту и создает спрайты по этой карте
 */
class Map(val container: Container) {
    
    fun draw(tiles: List<List<Tile>>) {
        tiles.forEach {
            it.forEach { tile ->
                container
                    .sprite(tile.type.animatedSprite())
                    .position(16 * tile.x, 16 * tile.y)
                    .playAnimation()
            }
        }
    }
}