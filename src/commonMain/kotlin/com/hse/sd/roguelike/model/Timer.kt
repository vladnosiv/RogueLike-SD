package com.hse.sd.roguelike.model

import com.hse.sd.roguelike.model.actions.Action

// class that holds current time and controlling temporal events
class Timer {
    var timer: Int = 0
        get() = field

    private val queue = mutableListOf<Pair<Int, () -> List<Action>>>()

    private fun executeCurrent(): List<Action> {
        return queue.filter { it.first == timer }.map { it.second.invoke()}.flatMap { it.asIterable() }
    }
    fun tick(): List<Action> {
        val actions = executeCurrent().toMutableList()

        queue.removeAll { it.first == timer }

        timer++
        return actions
    }

    fun addTask(delay: Int, function: () -> List<Action>) {
        assert (delay >= 0)
        queue.add(Pair(timer + delay, function))
    }
}
