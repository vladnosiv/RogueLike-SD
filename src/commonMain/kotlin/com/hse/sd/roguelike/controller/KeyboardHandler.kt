package com.hse.sd.roguelike.controller


class KeyboardHandler {

    private val commands = ArrayDeque<Command>()

    fun onCommand(cmd: Command) = commands.addLast(cmd)

    fun getAll() = commands.asIterable()

    fun clear() = commands.clear()
}