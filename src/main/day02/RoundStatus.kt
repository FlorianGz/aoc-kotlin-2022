package main.day02

enum class RoundStatus(val value: String, val point: Int) {
    LOOSE(value = "X", point = 0),
    DRAW(value = "Y", point = 3),
    WIN(value = "Z", point = 6);

    companion object {
        fun get(value: String): RoundStatus {
            return RoundStatus.values().toList().first { it.value == value }
        }
    }
}