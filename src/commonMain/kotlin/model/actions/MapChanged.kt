package model.actions

import model.Tile

data class MapChanged(val field: List<List<Tile>>): Action
