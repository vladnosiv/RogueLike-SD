package com.hse.sd.roguelike.model.items

import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actors.MainCharacter

interface Item {
    val type: ItemType
    var isEquipped: Boolean

    fun onThrow(): List<Action> = emptyList()
    fun onEquip(hero: MainCharacter): List<Action> = emptyList()
    fun onUnEquip(hero: MainCharacter): List<Action> = emptyList()
}
