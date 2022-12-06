package main.day06

import readInput

fun main() {

    fun part1(): Int {
        return getSignal(distinctNumberRequired = 4)
    }

    fun part2(): Int {
        return getSignal(distinctNumberRequired = 14)
    }

    println(part1())
    println(part2())
}

fun getSignal(distinctNumberRequired: Int): Int {
    val input = readInput("main/day06/day06").first()
    return input.windowed(distinctNumberRequired, 1).mapIndexed { index, signal ->
        if (signal.allUnique()) {
            index + distinctNumberRequired
        } else 0
    }.first { it != 0 }
}

fun String.allUnique(): Boolean = all(hashSetOf<Char>()::add)
