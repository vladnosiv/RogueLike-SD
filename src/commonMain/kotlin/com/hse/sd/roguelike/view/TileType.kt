package com.hse.sd.roguelike.view

import com.soywiz.korge.view.SpriteAnimation
import com.hse.sd.roguelike.view.sprites.TileAnimation


enum class TileType {
    FLOOR {
        override fun animatedSprite() = TileAnimation.Map.getFloor()
    },

    WALL {
        override fun animatedSprite() = TileAnimation.Map.Wall
    };

    abstract fun animatedSprite(): SpriteAnimation
}