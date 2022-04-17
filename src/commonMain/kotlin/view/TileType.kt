package view

import com.soywiz.korge.view.SpriteAnimation


enum class TileType {
    FLOOR {
        override fun animatedSprite() = TileAnimation.Map.floor
    },

    WALL {
        override fun animatedSprite() = TileAnimation.Map.wall
    };

    abstract fun animatedSprite(): SpriteAnimation
}