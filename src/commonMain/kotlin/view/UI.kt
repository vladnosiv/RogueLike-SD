package view

import com.soywiz.korge.view.Camera
import com.soywiz.korge.view.container
import view.sprites.TileAnimation


class UI(camera: Camera) {

    private val mapContainer = camera.container()
    private val actorsContainer = camera.container()
    private val statusBarContainer = camera.container()
    private val statusBar = StatusBar(statusBarContainer)
    private val statusEventHandler = object: StatusEventHandler {
        override fun selectInventoryCell(index: Int) = statusBar.selectCell(index)
        override fun displayHP(hp: Int, maxHP: Int) = statusBar.displayHP(hp, maxHP)
    }

    interface EventHandler

    interface ActorEventHandler: EventHandler {
        suspend fun move(dx: Int, dy: Int)
        fun turn(direction: Direction)
        fun place(x: Int, y: Int)
        suspend fun hit()
    }

    interface MapEventHandler: EventHandler {
        fun fill(field: List<List<Tile>>)
    }

    interface StatusEventHandler: EventHandler {
        fun selectInventoryCell(index: Int)
        fun displayHP(hp: Int, maxHP: Int)
    }

    private fun createActorRepr(character: view.sprites.CharacterSprite,
                                weapon: view.sprites.Weapon) = object: ActorEventHandler {
        val actor = Character(actorsContainer, character, weapon)
        override suspend fun move(dx: Int, dy: Int) = actor.changePosition(dx, dy)
        override fun turn(direction: Direction) = actor.changeDirection(direction)
        override fun place(x: Int, y: Int) = actor.setPosition(x, y)
        override suspend fun hit() = actor.hit()
    }

    fun createHeroRepr() = createActorRepr(TileAnimation.Characters.Knight, TileAnimation.Weapons.RegularSword)

    fun createMobRepr() = createActorRepr(TileAnimation.Characters.OrcWarrior, TileAnimation.Weapons.Axe)

    fun createMapRepr() = object: MapEventHandler {
        val map = Map(mapContainer)
        override fun fill(field: List<List<Tile>>) = map.draw(field)
    }

    fun getStatusRepr() = statusEventHandler
}
