
import com.soywiz.korev.Key
import com.soywiz.korge.*
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import view.sprites.TileAnimation

const val tileSize = 16
const val mapWidth = 32
const val mapHeight = 32


suspend fun main() = Korge(width = tileSize * mapWidth, height = tileSize * mapHeight) {
    val camera = this.camera()
    val mapContainer = camera.container()
    view.Map(mapContainer, mapWidth, mapHeight)
    val hero = view.Character(mapContainer, TileAnimation.Characters.Knight, TileAnimation.Weapons.RegularSword).also {
        it.setPosition(mapWidth / 2, mapHeight / 2)
    }
    val passiveMob = view.Character(mapContainer, TileAnimation.Characters.Wizard, TileAnimation.Weapons.MagicStick).also {
        it.setPosition(mapWidth - 4, mapWidth - 4)
    }
    val aggressiveMob = view.Character(mapContainer, TileAnimation.Characters.Necromancer).also {
        it.setPosition(mapWidth - 5, mapHeight - 5)
    }

    this.keys {
        down(Key.RIGHT) { hero.changePosition(1, 0) }
        down(Key.LEFT) { hero.changePosition(-1, 0) }
        down(Key.UP) { hero.changePosition(0, -1) }
        down(Key.DOWN) { hero.changePosition(0, 1) }
        down(Key.D) { hero.changePosition(1, 0) }
        down(Key.A) { hero.changePosition(-1, 0) }
        down(Key.W) { hero.changePosition(0, -1) }
        down(Key.S) { hero.changePosition(0, 1) }
        down(Key.SPACE) { hero.hit() }
    }
}