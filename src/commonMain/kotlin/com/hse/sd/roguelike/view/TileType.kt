package com.hse.sd.roguelike.view

import com.hse.sd.roguelike.view.sprites.TileAnimation
import com.soywiz.korge.view.SpriteAnimation


enum class TileType {
    FLOOR {
        override fun animatedSprite() = TileAnimation.Map.getFloor()
    },

    WALL {
        override fun animatedSprite() = TileAnimation.Map.Wall
    };

    abstract fun animatedSprite(): SpriteAnimation
}