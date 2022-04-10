
import com.soywiz.korev.Key
import com.soywiz.korge.*
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*

val tileSize = 16
val mapWidth = 32
val mapHeight = 32


suspend fun main() = Korge(width = tileSize * mapWidth, height = tileSize * mapHeight) {
    val camera = this.camera()
    val mapContainer = camera.container()
    val map = view.Map(mapContainer, mapWidth, mapHeight)
    val hero = view.Hero(mapContainer)
    val posX = mapWidth / 2
    val posY = mapHeight / 2
    hero.setPosition(posX, posY)

    this.keys {
        down(Key.RIGHT) { hero.changePosition(1, 0) }
        down(Key.LEFT) { hero.changePosition(-1, 0) }
        down(Key.UP) { hero.changePosition(0, -1) }
        down(Key.DOWN) { hero.changePosition(0, 1) }
        down(Key.D) { hero.changePosition(1, 0) }
        down(Key.A) { hero.changePosition(-1, 0) }
        down(Key.W) { hero.changePosition(0, -1) }
        down(Key.S) { hero.changePosition(0, 1) }
    }
}