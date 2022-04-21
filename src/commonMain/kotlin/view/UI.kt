package view

import com.soywiz.korge.view.Camera
import com.soywiz.korge.view.container
import view.sprites.TileAnimation


class UI(camera: Camera) {

    private val mapContainer = camera.container()
    private val actorsContainer = camera.container()
    private val statusBarContainer = camera.container()
    private val statusBar = StatusBar(statusBarContainer)

    interface EventHandler

    interface ActorEventHandler: EventHandler {
        suspend fun move(dx: Int, dy: Int)
        fun turnLeft()
        fun turnRight()
        fun turnUp()
        fun turnDown()
        fun place(x: Int, y: Int)
        suspend fun hit()
    }

    interface MapEventHandler: EventHandler {
        fun fill(field: List<List<Tile>>)
    }

    fun createActorRepr() = object: ActorEventHandler {
        val actor = Character(actorsContainer, TileAnimation.Characters.Knight, TileAnimation.Weapons.RegularSword)
        override suspend fun move(dx: Int, dy: Int) = actor.changePosition(dx, dy)
        override fun turnLeft() = actor.flipX()
        override fun turnRight() = actor.flipX()
        override fun turnUp() {}
        override fun turnDown() {}
        override fun place(x: Int, y: Int) = actor.setPosition(x, y)
        override suspend fun hit() = actor.hit()
    }

    fun createMapRepr() = object: MapEventHandler {
        val map = Map(mapContainer)
        override fun fill(field: List<List<Tile>>) = map.draw(field)
    }

    fun displayHp(hp: Int, maxHp: Int) {
        statusBar.displayHP(hp, maxHp)
    }
}
