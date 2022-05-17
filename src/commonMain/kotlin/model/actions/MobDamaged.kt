package model.actions

import model.actors.mobs.Mob

data class MobDamaged(val mob: Mob, val damage: Int) : Action
