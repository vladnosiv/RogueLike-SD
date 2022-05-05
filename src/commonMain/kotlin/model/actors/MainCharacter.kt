package model.actors

import model.Direction
import model.Position
import model.actions.*

// class for main character
class MainCharacter(position: Position, hp: Int, power: Int, var exp: Int) : Actor(position, hp, power) {
    var direction = Direction.RIGHT
    var lvl = 0

    fun makeMove(direction: Direction): List<Action> {
        return if (this.direction != direction) {
            this.direction = direction
            position += direction
            listOf(
                HeroChangedDirection(direction),
                HeroMoved(direction.deltaX, direction.deltaY)
            )
        } else {
            position += direction
            listOf(HeroMoved(direction.deltaX, direction.deltaY))
        }
    }

    private fun enoughExp(): Boolean {
        return exp >= (2 shl lvl)
    }

    private fun lvlUp() {
        power++
        lvl++
    }

    fun addExp(value: Int) {
        exp += value
        while (enoughExp()) {
            lvlUp()
        }
    }

    fun attack(): List<Action> {
        return listOf(
            HeroAttacked(direction)
        )
    }

    fun attack(direction: Direction): List<Action> {
        return if (this.direction != direction) {
            this.direction = direction
            listOf(
                HeroChangedDirection(direction),
                HeroAttacked(direction)
            )
        } else {
            listOf(HeroAttacked(direction))
        }
    }

    override fun onAttack(power: Int): List<Action> {
        val actions = mutableListOf<Action>()
        actions.add(HeroDamaged(power))
        hp -= power
        if (hp <= 0) {
            actions.addAll(onKill())
        }
        return actions
    }

    override fun onKill(): List<Action> {
        return listOf(
            HeroKilled()
        )
    }
}
