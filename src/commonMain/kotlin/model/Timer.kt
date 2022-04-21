package model

import model.actions.Action

// class that holds current time and controlling temporal events
class Timer {
    var timer: Int = 0
        get() = field

    fun tick(): List<Action> {
        timer++
        return emptyList()
    }
}
