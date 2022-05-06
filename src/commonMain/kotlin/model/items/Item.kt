package model.items

import model.actions.Action
import model.actors.MainCharacter

interface Item {
    val type: ItemType
    var isEquipped: Boolean

    fun onThrow(): List<Action> = emptyList()
    fun onEquip(hero: MainCharacter): List<Action> = emptyList()
    fun onUnEquip(hero: MainCharacter): List<Action> = emptyList()
}
