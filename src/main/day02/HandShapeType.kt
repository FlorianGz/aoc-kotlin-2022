package main.day02

enum class HandShapeType(val value: String, val winAgainst: String, val looseAgain: String, val point: Int) {
    Rock(value = "AX", winAgainst = "CZ", looseAgain = "BY", point = 1),
    Paper(value = "BY", winAgainst = "AX", looseAgain = "CZ", point = 2),
    Scissor(value = "CZ", winAgainst = "BY", looseAgain = "AX",  point = 3);

    companion object {
        fun get(value: String): HandShapeType {
            return HandShapeType.values().toList().first { it.value.contains(value) }
        }
    }
}