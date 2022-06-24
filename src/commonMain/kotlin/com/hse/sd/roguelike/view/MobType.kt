package com.hse.sd.roguelike.view

import com.hse.sd.roguelike.view.sprites.TileAnimation
import com.hse.sd.roguelike.view.sprites.CharacterSprite


enum class MobType {
    ORC {
        override fun characterSprite() = TileAnimation.Characters.OrcWarrior
    },

    MASKED_ORC {
        override fun characterSprite() = TileAnimation.Characters.MaskedOrc
    },

    SKELETON {
        override fun characterSprite() = TileAnimation.Characters.Skeleton
    },

    BIG_ZOMBIE {
        override fun characterSprite() = TileAnimation.Characters.BigZombie
    },

    CHORT {
        override fun characterSprite() = TileAnimation.Characters.Chort
    },

    POISONOUS_MOLD {
        override fun characterSprite() = TileAnimation.Characters.Swampy
    },

    DRAGON {
        override fun characterSprite() = TileAnimation.Characters.Lizard
    };

    abstract fun characterSprite(): CharacterSprite
}
