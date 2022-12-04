package main.day03

import readInput

fun main() {
    val elfBags = readInput("main/day03/day03")

    fun part1(): Int {
        return elfBags.map {
            val (bagPart1, bagPart2) = it.chunked(size = (it.length) / 2)
            bagPart1.first { bagItem -> bagPart2.contains(bagItem) }
        }.sumOf { it.toPriority() }
    }

    fun part2(): Int {
        return elfBags.chunked(3)
            .map { group ->
                val (bag1, bag2, bag3) = group
                bag1.first { bagItem -> bag2.contains(bagItem) && bag3.contains(bagItem) }
            }.sumOf { it.toPriority() }
    }

    println(part1())
    println(part2())
}

private fun Char.toPriority(): Int {
    return if (isLowerCase()) this - 'a' + 1 else this - 'A' + 27
}