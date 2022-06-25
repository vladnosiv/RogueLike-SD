package com.hse.sd.roguelike.model.effects

import com.hse.sd.roguelike.model.Environment

//effect generator
class EffectFactory(val environment: Environment) {
    fun getEffect(type: EffectType): AbstractEffect {
        return when (type) {
            EffectType.CONFUSION -> Confusion(environment)
        }
    }
}
