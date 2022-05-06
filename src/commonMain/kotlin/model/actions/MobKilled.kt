package model.actions

import model.actors.Mob

data class MobKilled(val mob: Mob) : Action
