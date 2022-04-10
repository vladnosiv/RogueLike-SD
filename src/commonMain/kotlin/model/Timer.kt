package model

// class that holds current time and controlling temporal events
class Timer {
    var timer: Int = 0
        get() = field

    fun tick() {
        timer++
    }
}
