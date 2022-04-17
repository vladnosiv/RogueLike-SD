package model

// class that stores information about a mob
class Mob(position: Position, hp: Int) : Actor(position, hp) {
    var strategy: Strategy? = null
}
