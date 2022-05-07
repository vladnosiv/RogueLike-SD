package view

import com.soywiz.korge.view.*
import com.soywiz.korma.geom.*
import view.sprites.CharacterSprite
import view.sprites.TileAnimation


class UI(camera: Camera) {

    private val mapContainer       = camera.container()
    private val itemsContainer     = camera.container()
    private val actorsContainer    = camera.container()
    private val weaponsContainer   = camera.container()
    private val statusBarContainer = camera.container()
    
    private val statusBar          = StatusBar(statusBarContainer)
    private val statusEventHandler = object: StatusEventHandler {
        override fun selectInventoryCell(index: Int)         = statusBar.selectCell(index)
        override fun displayHP(hp: Int, maxHP: Int)          = statusBar.displayHP(hp, maxHP)
        override fun putItem(itemType: ItemType, index: Int) = statusBar.addItem(index, itemType)
        override fun removeItem(index: Int)                  = statusBar.delItem(index)
    }

    interface EventHandler

    interface ActorEventHandler: EventHandler {
        suspend fun move(dx: Int, dy: Int)
        fun turn(direction: Direction)
        fun place(x: Int, y: Int)
        suspend fun hit()
        fun equipItem(itemType: ItemType)
        fun unequipItem()
        fun kill()
    }

    interface ItemEventHandler: EventHandler {
        fun place(x: Int, y: Int)
        fun remove()
    }

    interface MapEventHandler: EventHandler {
        fun fill(field: List<List<Tile>>)
    }

    interface StatusEventHandler: EventHandler {
        fun selectInventoryCell(index: Int)
        fun displayHP(hp: Int, maxHP: Int)
        fun putItem(itemType: ItemType, index: Int)
        fun removeItem(index: Int)
    }

    private fun createActorRepr(character: CharacterSprite) = object: ActorEventHandler {
        val actor = Character(actorsContainer, weaponsContainer, character)

        override suspend fun move(dx: Int, dy: Int) = actor.changePosition(dx, dy)
        override fun turn(direction: Direction)     = actor.changeDirection(direction)
        override fun place(x: Int, y: Int)          = actor.setPosition(x, y)
        override suspend fun hit()                  = actor.hit()
        override fun equipItem(itemType: ItemType)  = actor.setWeapon(itemType)
        override fun unequipItem()                  = actor.removeWeapon()
        override fun kill()                         = actor.remove()
    }

    fun createHeroRepr() = createActorRepr(TileAnimation.Characters.Knight)

    fun createMobRepr(): ActorEventHandler {
        var mobRepr = createActorRepr(TileAnimation.Characters.OrcWarrior)
        mobRepr.equipItem(ItemType.AXE)
        return mobRepr
    }

    fun createItemRepr(itemType: ItemType) = object: ItemEventHandler {
        val itemType = itemType 
        val sprite   = itemsContainer.sprite(TileAnimation.UI.Transparent)

        override fun place(x: Int, y: Int) {
            sprite.playAnimation(itemType.animatedSprite())
            sprite.position(x, y)
            sprite.rotation(45.degrees)
        }
        override fun remove() = sprite.playAnimation(TileAnimation.UI.Transparent)
    }

    fun createMapRepr() = object: MapEventHandler {
        val map = Map(mapContainer)
        override fun fill(field: List<List<Tile>>) = map.draw(field)
    }

    fun getStatusRepr() = statusEventHandler
}
