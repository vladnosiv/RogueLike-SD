package model.actions

import model.actors.Actor
import model.Position
import model.actors.mobs.Mob

data class MobCreated(val position: Position, val actor: Mob): Action
