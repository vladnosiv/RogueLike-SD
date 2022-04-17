package view

import com.soywiz.korge.view.Camera


class UI(camera: Camera) {

    private var mapContainer = camera

    interface EventHandler {}

    interface ActorEventHandler: UI.EventHandler {
        abstract fun move(dx: Int, dy: Int)
        abstract fun place(x: Int, y: Int)
    }

    interface MapEventHandler: UI.EventHandler {
        abstract fun fill(field: List<List<Tile>>)
    }

    fun createActorRepr() = object: UI.ActorEventHandler {
        val actor = Hero(mapContainer)
        override fun move(dx: Int, dy: Int) = actor.changePosition(dx, dy)
        override fun place(x: Int, y: Int) = actor.setPosition(x, y)
    }

    fun createMapRepr() = object: UI.MapEventHandler {
        val map = Map(mapContainer)
        override fun fill(field: List<List<Tile>>) = map.draw(field)
    }
}
