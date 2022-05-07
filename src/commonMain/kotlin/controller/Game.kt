package controller

import kotlin.collections.HashMap
import kotlinx.coroutines.*
import model.actions.*


class Game(private val ui: view.UI,
           private val logic: model.ModelHandler,
           private val commands: KeyboardHandler) {

    private val statusUIRepr = ui.getStatusRepr()
    private val mapUIRepr    = ui.createMapRepr()
    private val heroUIRepr   = ui.createHeroRepr()
    private val mobsUIReprs  = HashMap<model.actors.Actor, view.UI.ActorEventHandler>()
    private val itemsUIReprs = HashMap<model.items.Item, view.UI.ItemEventHandler>()

    init {
        GlobalScope.launch{ tick() }
    }

    suspend fun tick() {
        handleCommands()
        displayActions()
    }

    suspend private fun handleCommands() {
        for (cmd: Command in commands.getAll()) {
            when (cmd) {
                Command.MOVE_UP      -> logic.onMove(model.Direction.UP)
                Command.MOVE_DOWN    -> logic.onMove(model.Direction.DOWN)
                Command.MOVE_LEFT    -> logic.onMove(model.Direction.LEFT)
                Command.MOVE_RIGHT   -> logic.onMove(model.Direction.RIGHT)
                // ʘ‿ʘ
                Command.SELECT_ITEM0 -> {
                    statusUIRepr.selectInventoryCell(0)
                    logic.onEquip(0)
                }
                Command.SELECT_ITEM1 -> {
                    statusUIRepr.selectInventoryCell(1)
                    logic.onEquip(1)
                }
                Command.SELECT_ITEM2 -> {
                    statusUIRepr.selectInventoryCell(2)
                    logic.onEquip(2)
                }
                Command.SELECT_ITEM3 -> {
                    statusUIRepr.selectInventoryCell(3)
                    logic.onEquip(3)
                }
                Command.SELECT_ITEM4 -> {
                    statusUIRepr.selectInventoryCell(4)
                    logic.onEquip(4)
                }
                Command.ATTACK       -> logic.onAttack()
                Command.PICK_UP      -> logic.onPick()
                Command.THROW        -> logic.onThrow()
            }
        }
        commands.clear()
    }

    suspend private fun displayActions() {
        for (action: Action in logic.onTick()) {
            println(action::class.simpleName)
            when (action) {
                is HeroPlaced           -> heroUIRepr.place(action.position.x, action.position.y)
                is HeroHPChanged        -> statusUIRepr.displayHP(action.current, action.max)
                is HeroMoved            -> heroUIRepr.move(action.dx, action.dy)
                is HeroChangedDirection -> heroUIRepr.turn(viewDirection(action.direction))
                is HeroAttacked         -> heroUIRepr.hit()
                is MobCreated           -> {
                    mobsUIReprs.put(action.actor, ui.createMobRepr())
                    mobsUIReprs.get(action.actor)?.place(action.position.x, action.position.y)
                }
                is MobAttacked          -> mobsUIReprs.get(action.actor)?.hit()
                is MobMoved             -> mobsUIReprs.get(action.actor)?.move(action.dx, action.dy)
                is MobKilled            -> {
                    mobsUIReprs.get(action.mob)?.kill()
                    mobsUIReprs.remove(action.mob)
                }
                is ItemCreated          -> {
                    itemsUIReprs.put(action.item, ui.createItemRepr(viewItemType(action.item.type)))
                    itemsUIReprs.get(action.item)?.place(action.pos.x, action.pos.y)
                }
                is ItemAdded            -> statusUIRepr.putItem(viewItemType(action.item.type), action.pos)
                is ItemPickedByHero     -> itemsUIReprs.get(action.item)?.remove()
                is ItemEquipped         -> heroUIRepr.equipItem(viewItemType(action.item.type))
                is ItemUnEquipped       -> heroUIRepr.unequipItem()
                is ItemThrown           -> itemsUIReprs.get(action.item)?.place(action.position.x, action.position.y)
                is MapChanged           -> {
                    mapUIRepr.fill(action.field.map { row ->
                        row.map { tile ->
                            when (tile.type) {
                                model.map.TileType.FLOOR -> view.Tile(view.TileType.FLOOR, tile.x, tile.y)
                                else -> view.Tile(view.TileType.WALL, tile.x, tile.y)
                            }
                        }
                    })
                }
            }
        }
    }

    private fun viewDirection(direction: model.Direction): view.Direction {
        return when (direction) {
            model.Direction.UP   -> view.Direction.UP
            model.Direction.DOWN -> view.Direction.DOWN
            model.Direction.LEFT -> view.Direction.LEFT
            else                 -> view.Direction.RIGHT
        }
    }

    private fun viewItemType(itemType: model.items.ItemType): view.ItemType {
        return when (itemType) {
            model.items.ItemType.SWORD -> view.ItemType.REGULAR_SWORD
            else                       -> view.ItemType.NONE
        }
    }
}
