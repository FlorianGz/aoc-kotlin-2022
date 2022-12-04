package main.day04

import readInput
import kotlin.math.max
import kotlin.math.min

fun main() {
    val elfSections = readInput("main/day04/day04")

    fun part1(): Int {
        return elfSections.map { it.asRanges() }.count { (range1, range2)  ->
            range1.containedIn(range2) >= 2 || range2.containedIn(range1) >= 2
        }
    }

    fun part2(): Int {
        return elfSections.map { it.asRanges() }.count { (range1, range2)  ->
            range1.containedIn(range2) >= 0 || range2.containedIn(range1) >= 0
        }
    }

    println(part1())
    println(part2())
}

private fun String.asRanges(): Pair<IntRange,IntRange> = substringBefore(",").asIntRange() to substringAfter(",").asIntRange()
private fun String.asIntRange(): IntRange = substringBefore("-").toInt() .. substringAfter("-").toInt()
private fun IntRange.containedIn(other: IntRange) = (min(endInclusive, other.last) - max(start, other.first)).coerceAtLeast(-1)
