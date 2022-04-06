package model

class Mob(position: Position, hp: Int): Actor(position, hp) {
    var strategy: Strategy? = null
}
