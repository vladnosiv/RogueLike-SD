package model.items

import model.actions.Action
import model.actions.ItemEquipped
import model.actions.ItemUnEquipped
import model.actors.MainCharacter

class Sword : Item {
    override val type = ItemType.SWORD
    override var isEquipped = false

    private val powerAdd = 3

    override fun onEquip(hero: MainCharacter): List<Action> {
        assert(!isEquipped)
        isEquipped = true

        hero.power += powerAdd
        return listOf(
            ItemEquipped(this)
        )
    }

    override fun onUnEquip(hero: MainCharacter): List<Action> {
        assert(isEquipped)
        isEquipped = false

        hero.power -= powerAdd
        return listOf(
            ItemUnEquipped(this)
        )
    }
}