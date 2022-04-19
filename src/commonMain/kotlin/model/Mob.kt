package model

// class that stores information about a mob
class Mob(position: Position, hp: Int) : Actor(position, hp) {
    fun makeMove(): List<Action> {
        return emptyList() //TODO("Not yet implemented")
    }

    var strategy: Strategy? = null
}
