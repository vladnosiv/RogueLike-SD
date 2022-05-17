package model.actions

import model.actors.mobs.Mob

data class MobKilled(val mob: Mob) : Action
