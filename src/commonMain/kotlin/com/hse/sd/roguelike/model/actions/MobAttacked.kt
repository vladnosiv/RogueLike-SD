package com.hse.sd.roguelike.model.actions

import com.hse.sd.roguelike.model.Direction
import com.hse.sd.roguelike.model.actors.Actor

data class MobAttacked(val actor: Actor, val direction: Direction): Action
