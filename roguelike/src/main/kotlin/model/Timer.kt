package model

class Timer {
    private var timer: Int = 0
        get() = field

    fun tick() {
        timer++
    }
}
