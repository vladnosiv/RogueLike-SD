package com.hse.sd.roguelike.controller

import com.hse.sd.roguelike.model.ModelHandler
import kotlin.collections.HashMap
import kotlinx.coroutines.*
import com.hse.sd.roguelike.model.actions.*
import com.hse.sd.roguelike.model.actors.Actor
import com.hse.sd.roguelike.model.items.Item
import com.hse.sd.roguelike.view.*


class Game(private val ui: UI,
           private val logic: ModelHandler,
           private val commands: KeyboardHandler
) {

    private val statusUIRepr = ui.getStatusRepr()
    private val mapUIRepr    = ui.createMapRepr()
    private val heroUIRepr   = ui.createHeroRepr()
    private val mobsUIReprs  = HashMap<Actor, UI.ActorEventHandler>()
    private val itemsUIReprs = HashMap<Item, UI.ItemEventHandler>()

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
                Command.MOVE_UP -> logic.onMove(com.hse.sd.roguelike.model.Direction.UP)
                Command.MOVE_DOWN -> logic.onMove(com.hse.sd.roguelike.model.Direction.DOWN)
                Command.MOVE_LEFT -> logic.onMove(com.hse.sd.roguelike.model.Direction.LEFT)
                Command.MOVE_RIGHT -> logic.onMove(com.hse.sd.roguelike.model.Direction.RIGHT)
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
                Command.ATTACK -> logic.onAttack()
                Command.PICK_UP -> logic.onPick()
                Command.THROW -> logic.onThrow()
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
                    mobsUIReprs.put(action.actor, ui.createMobRepr(viewMobType(action.actor.type)))
                    mobsUIReprs.get(action.actor)?.place(action.position.x, action.position.y)
                }
                is MobAttacked          -> mobsUIReprs.get(action.actor)?.hit()
                is MobMoved             -> mobsUIReprs.get(action.actor)?.move(action.dx, action.dy)
                is MobChangedDirection  -> mobsUIReprs.get(action.mob)?.turn(viewDirection(action.direction))
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
                                com.hse.sd.roguelike.model.map.TileType.FLOOR -> Tile(TileType.FLOOR, tile.x, tile.y)
                                else -> Tile(TileType.WALL, tile.x, tile.y)
                            }
                        }
                    })
                }
            }
        }
    }

    private fun viewDirection(direction: com.hse.sd.roguelike.model.Direction): Direction {
        return when (direction) {
            com.hse.sd.roguelike.model.Direction.UP   -> Direction.UP
            com.hse.sd.roguelike.model.Direction.DOWN -> Direction.DOWN
            com.hse.sd.roguelike.model.Direction.LEFT -> Direction.LEFT
            else                 -> Direction.RIGHT
        }
    }

    private fun viewItemType(itemType: com.hse.sd.roguelike.model.items.ItemType): ItemType {
        return when (itemType) {
            com.hse.sd.roguelike.model.items.ItemType.SWORD -> ItemType.REGULAR_SWORD
            else                       -> ItemType.NONE
        }
    }

    private fun viewMobType(mobType: com.hse.sd.roguelike.model.actors.mobs.MobType): MobType {
        return when (mobType) {
            com.hse.sd.roguelike.model.actors.mobs.MobType.ZOMBIE         -> MobType.ORC
            com.hse.sd.roguelike.model.actors.mobs.MobType.ZOMBIE_IN_MASK -> MobType.MASKED_ORC
            com.hse.sd.roguelike.model.actors.mobs.MobType.SKELETON       -> MobType.SKELETON
            com.hse.sd.roguelike.model.actors.mobs.MobType.DRAGON         -> MobType.DRAGON
            com.hse.sd.roguelike.model.actors.mobs.MobType.BIG_ZOMBIE     -> MobType.BIG_ZOMBIE
            com.hse.sd.roguelike.model.actors.mobs.MobType.POISONOUS_MOLD -> MobType.POISONOUS_MOLD
            else                                     -> MobType.CHORT
        }
    }
}
