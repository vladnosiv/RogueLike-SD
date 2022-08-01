package com.hse.sd.roguelike.model.map

import com.hse.sd.roguelike.model.Position
import com.hse.sd.roguelike.model.actions.Action
import com.hse.sd.roguelike.model.actions.HeroPlaced
import com.hse.sd.roguelike.model.actions.ItemCreated
import com.hse.sd.roguelike.model.actions.MobCreated
import com.hse.sd.roguelike.model.actors.MainCharacter
import com.hse.sd.roguelike.model.actors.mobs.Mob
import com.hse.sd.roguelike.model.items.Item


// class that stores a map of tiles
data class Map(val field: List<List<Tile>>) {
    // checking that the transmitted position is on the field

    fun moveActor(from: Position, to: Position): List<Action> {
        assert(isPositionOnField(from))
        assert(isPositionOnField(to))

        println("${from.toString()} ${to.toString()}")
        val fromTile = getTile(from)
        val toTile = getTile(to)

        assert(fromTile.actor != null)
        assert(toTile.actor == null)

        toTile.actor = fromTile.actor
        fromTile.actor = null

        assert(fromTile.actor == null)
        assert(toTile.actor != null)

        return emptyList()
    }

    fun createItem(pos: Position, item: Item): List<Action> {
        assert(isPositionOnField(pos))

        val tile = getTile(pos)

        assert(tile.item == null)

        tile.item = item
        return listOf(
            ItemCreated(pos, item)
        )
    }

    fun isPositionOnField(position: Position): Boolean {
        val yOK = (0 <= position.y && position.y < field.size)
        val xOK = yOK && (0 <= position.x && position.x < field[position.y].size)
        return xOK && yOK
    }

    // returns tile by position
    fun getTile(position: Position): Tile {
        assert(isPositionOnField(position))
        return field[position.x][position.y]
    }

    fun canStep(position: Position): Boolean {
        if (!isPositionOnField(position)) {
            return false
        }

        val tile = getTile(position)
        return tile.isEmpty() && tile.type.isPassable()
    }

    fun createMainCharacter(position: Position, mainCharacter: MainCharacter): List<Action> {
        val tile = getTile(position)
        assert(tile.actor == null)

        tile.actor = mainCharacter
        return listOf(
            HeroPlaced(position)
        )
    }

    fun createMob(position: Position, mob: Mob): List<Action> {
        val tile = getTile(position)
        assert(tile.actor == null)

        tile.actor = mob
        return listOf(
            MobCreated(position, mob)
        )
    }
}
