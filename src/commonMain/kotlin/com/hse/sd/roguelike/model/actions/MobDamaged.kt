package com.hse.sd.roguelike.model.actions

import com.hse.sd.roguelike.model.actors.mobs.Mob

data class MobDamaged(val mob: Mob, val damage: Int) : Action
