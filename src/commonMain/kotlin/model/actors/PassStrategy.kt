package model.actors

import model.Environment
import model.actions.Action

class PassStrategy(override val environment: Environment) : Strategy {
    override fun makeAct(mob: Mob): List<Action> {
        return emptyList()
    }
}