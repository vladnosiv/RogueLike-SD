package model.actors

import model.Position

data class MobConfig(val position: Position, val hp: Int, val strategy: Strategy)
