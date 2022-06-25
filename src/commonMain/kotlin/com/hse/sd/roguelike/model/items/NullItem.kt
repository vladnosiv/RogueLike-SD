package com.hse.sd.roguelike.model.items

class NullItem : Item {
    override val type = ItemType.NULL
    override var isEquipped = false
}