package view

import com.soywiz.korge.view.SpriteAnimation
import view.sprites.TileAnimation


enum class TileType {
    FLOOR {
        override fun animatedSprite() = TileAnimation.Map.getFloor()
    },

    WALL {
        override fun animatedSprite() = TileAnimation.Map.wall
    };

    abstract fun animatedSprite(): SpriteAnimation
}