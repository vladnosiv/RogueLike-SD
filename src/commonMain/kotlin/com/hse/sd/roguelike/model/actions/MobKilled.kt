package com.hse.sd.roguelike.model.actions

import com.hse.sd.roguelike.model.actors.mobs.Mob

data class MobKilled(val mob: Mob) : Action
