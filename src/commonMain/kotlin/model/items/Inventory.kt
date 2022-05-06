package model.items

import model.actions.*
import model.actors.MainCharacter

class Inventory {
    private val items = Array<Item>(5) { NullItem() }

    fun addItem(item: Item): List<Action> {
        for (i in items.indices) {
            if (items[i] is NullItem) {
                items[i] = item
                return listOf(
                    ItemPickedByHero(),
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
            item.onEquip(hero)
        } else {
            item.onUnEquip(hero)
        }
    }

    fun throwItem(pos: Int, hero: MainCharacter, map: model.map.Map): List<Action> {
        val item = items[pos]

        if (item is NullItem) {
            return emptyList()
        }

        val tile = map.getTile(hero.position)

        return if (tile.item == null) {
            listOf(
                ItemThrown(item, hero.position)
            )
        } else {
            listOf()
        }
    }

}
