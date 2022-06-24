package com.hse.sd.roguelike.model.actions

import com.hse.sd.roguelike.model.Position
import com.hse.sd.roguelike.model.actors.mobs.Mob

data class MobCreated(val position: Position, val actor: Mob): Action
