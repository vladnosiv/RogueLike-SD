package model.actors

import model.Position

data class MobConfig(val position: Position, val hp: Int, val power: Int, val strategy: Strategy)
