package model.map

import model.LevelHolder
import java.io.BufferedReader
import kotlin.streams.toList

// a class that loads a field from a text file
class MapLoader(levelName: String) : MapGenerator {
    private val field: List<List<Tile>>

    private val tiles = mapOf(
        '.' to TileType.FLOOR,
        '#' to TileType.WALL
    )

    init {
        val path = LevelHolder.getPathByName(levelName)
        val bufferedReader: BufferedReader = path.toFile().bufferedReader()
        // TODO: FIX TILE POSITION!!!
        field = bufferedReader.lines().toList()
            .mapIndexed { x, it -> it.mapIndexed { y, char -> Tile(tiles[char]!!, x, y) }.toList() }.toList()
    }

    override fun genMap(): Map {
        return Map(field)
    }
}
