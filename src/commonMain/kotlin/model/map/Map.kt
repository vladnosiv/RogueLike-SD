package model.map

import model.Position
import model.actions.Action
import model.actions.HeroPlaced
import model.actions.MobCreated
import model.actors.MainCharacter
import model.actors.Mob

// class that stores a map of tiles
data class Map(val field: List<List<Tile>>) {
    // checking that the transmitted position is on the field

    fun moveActor(from: Position, to: Position): List<Action> {
        assert(isPositionOnField(from))
        assert(isPositionOnField(to))

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
