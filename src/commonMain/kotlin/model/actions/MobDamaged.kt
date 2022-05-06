package model.actions

import model.actors.Mob

data class MobDamaged(val mob: Mob, val damage: Int) : Action
