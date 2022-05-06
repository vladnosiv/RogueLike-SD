package model.actors

import model.Position

data class MobConfig(val position: Position, val hp: Int, val power: Int, val keepExp: Int, val strategy: Strategy)
