package view

import com.soywiz.korge.view.Camera
import com.soywiz.korge.view.container
import view.sprites.TileAnimation


class UI(camera: Camera) {

    private val mapContainer = camera.container()
    private val actorsContainer = camera.container()

    interface EventHandler

    interface ActorEventHandler: EventHandler {
        suspend fun move(dx: Int, dy: Int)
        fun place(x: Int, y: Int)
    }

    interface MapEventHandler: EventHandler {
        fun fill(field: List<List<Tile>>)
    }

    fun createActorRepr() = object: ActorEventHandler {
        val actor = Character(actorsContainer, TileAnimation.Characters.Knight, TileAnimation.Weapons.RegularSword)
        override suspend fun move(dx: Int, dy: Int) = actor.changePosition(dx, dy)
        override fun place(x: Int, y: Int) = actor.setPosition(x, y)
    }

    fun createMapRepr() = object: MapEventHandler {
        val map = Map(mapContainer)
        override fun fill(field: List<List<Tile>>) = map.draw(field)
    }
}
