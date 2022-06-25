package com.hse.sd.roguelike.model.items

import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actions.ItemAdded
import com.hse.sd.roguelike.model.actions.ItemPickedByHero
import com.hse.sd.roguelike.model.actions.ItemThrown
import com.hse.sd.roguelike.model.actors.MainCharacter
import com.hse.sd.roguelike.model.map.Map

//inventory for main character
class Inventory {
    private val items = Array<Item>(5) { NullItem() }

    fun getItem(pos: Int): Item {
        return items[pos]
    }

    fun addItem(item: Item): List<Action> {
        for (i in items.indices) {
            if (items[i] is NullItem) {
                items[i] = item
                return listOf(
                    ItemPickedByHero(i, item),
                    ItemAdded(i, item)
                )
            }
        }
        return emptyList()
    }

    fun changeEquippedStatus(pos: Int, hero: MainCharacter): List<Action> {
        val item = items[pos]

        if (item is NullItem) {
            return emptyList()
        }

        return if (item.isEquipped) {
            item.onUnEquip(hero)
        } else {
            item.onEquip(hero)
        }
    }

    fun throwItem(pos: Int, hero: MainCharacter, map: Map): List<Action> {
        val item = items[pos]

        if (item is NullItem) {
            return emptyList()
        }

        val tile = map.getTile(hero.position)

        return if (tile.item == null) {
            items[pos] = NullItem()
            listOf(
                ItemThrown(item, hero.position)
            )
        } else {
            listOf()
        }
    }

}
