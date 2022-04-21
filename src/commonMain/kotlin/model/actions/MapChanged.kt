package model.actions

import model.map.Tile

data class MapChanged(val field: List<List<Tile>>): Action
