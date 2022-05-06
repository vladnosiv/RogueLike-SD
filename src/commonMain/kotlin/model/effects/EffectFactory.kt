package model.effects

import model.Environment

class EffectFactory(val environment: Environment) {
    fun getEffect(type: EffectType): AbstractEffect {
        return when (type) {
            EffectType.CONFUSION -> Confusion(environment)
        }
    }
}
