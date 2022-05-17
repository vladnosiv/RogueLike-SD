package model.actions

import model.Direction
import model.actors.mobs.Mob

data class MobChangedDirection(val mob: Mob, val direction: Direction) : Action {}
