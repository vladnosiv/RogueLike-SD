package com.hse.sd.roguelike.view

import com.soywiz.korge.view.SpriteAnimation
import com.hse.sd.roguelike.view.sprites.TileAnimation


enum class ItemType {
    REGULAR_SWORD {
        override fun animatedSprite() = TileAnimation.Weapons.RegularSword.sprite
    },

    HAMMER {
        override fun animatedSprite() = TileAnimation.Weapons.Hammer.sprite
    },

    AXE {
        override fun animatedSprite() = TileAnimation.Weapons.Axe.sprite
    },

    NONE {
        override fun animatedSprite() = TileAnimation.UI.Transparent
    };

    abstract fun animatedSprite(): SpriteAnimation
}