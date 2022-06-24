package com.hse.sd.roguelike.model.actions

import com.hse.sd.roguelike.model.Direction
import com.hse.sd.roguelike.model.actors.mobs.Mob

data class MobChangedDirection(val mob: Mob, val direction: Direction) : Action
