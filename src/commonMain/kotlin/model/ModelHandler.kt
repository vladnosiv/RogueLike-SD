package model

import model.actions.*
import java.util.*

// class that stores the model
class ModelHandler {
    private val logic = ModelLogic()
    private var actions: MutableList<Action>
    private var canMove = true
    private var canAttack = true

    init {
        actions = logic.newGame().toMutableList()
    }

    // handles move action
    fun onMove(direction: Direction) {
        if (logic.canMainCharacterMove(direction) && canMove) {
            actions.addAll(logic.mainCharacterMove(direction))
            canMove = false
            canAttack = false
        }
    }

    fun onAttack() {
        if (canAttack) {
            actions.addAll(logic.mainCharacterAttack())
            canAttack = false
        }
    }

    // returns all actions from last tick
    fun onTick(): List<Action> {
        val actionsFromLastTick = actions

        actions = mutableListOf()
        canMove = true
        canAttack = true

        val queue = LinkedList<Action>()
        queue.addAll(actionsFromLastTick)
        queue.addAll(logic.tick())
//         TODO("Ensure one HeroMoved action on list")
        for (action in queue) {
            queue.addAll(handleAction(action))
        }
        return queue.toList()
    }

    private fun handleAction(action: Action): List<Action> {
        return when (action) { // TODO("Other actions")
            is MobMoved -> {
                val direction = Direction(action.dx, action.dy)
                val pos = action.actor.position
                logic.environment.map.moveActor(pos - direction, pos)
            }
            else -> emptyList()
        }
    }

}
