package com.hse.sd.roguelike.model.actions

import com.hse.sd.roguelike.model.map.Tile

data class MapChanged(val field: List<List<Tile>>): Action
