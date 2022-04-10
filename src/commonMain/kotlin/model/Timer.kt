package model

class Timer {
    var timer: Int = 0
        get() = field

    fun tick() {
        timer++
    }
}
