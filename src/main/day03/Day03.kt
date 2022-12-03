package main.day03

import readInput

fun main() {
    val elfBags = readInput("main/day03/day03")
    val alphabet = "abcdefghijklmnopqrstuvwxyz"
    val scoreBoard = alphabet + alphabet.uppercase()

    fun part1(): Int {
        val commonBagItems = elfBags.map {
            val (bagPart1, bagPart2) = it.chunked(size = (it.length) / 2)
            bagPart1.first { bagItem -> bagPart2.contains(bagItem) }
        }

        return commonBagItems.getScore(scoreBoard)
    }

    fun part2(): Int {
        val commonBagItems = elfBags.chunked(3).map { group ->
            val (bag1, bag2, bag3) = group
            bag1.first { bagItem -> bag2.contains(bagItem) && bag3.contains(bagItem) }
        }

        return commonBagItems.getScore(scoreBoard)
    }

    println(part1())
    println(part2())
}

private fun List<Char>.getScore(input: String) = this.sumOf {
    input.indexOf(it) + 1
}