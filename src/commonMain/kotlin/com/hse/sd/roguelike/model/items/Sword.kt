package com.hse.sd.roguelike.model.items

import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actions.ItemEquipped
import com.hse.sd.roguelike.model.actions.ItemUnEquipped
import com.hse.sd.roguelike.model.actors.MainCharacter

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