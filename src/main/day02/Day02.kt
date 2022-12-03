package main.day02

import readInput

fun main() {
    val puzzleInput = readInput("main/day02/day02")

    fun part1(): Int {
        var totalScore = 0

        puzzleInput.forEach {
            val (opponentTurn, myTurn) = it.split(" ")
            val opponentHandShape = HandShapeType.get(opponentTurn)
            val myHandShape = HandShapeType.get(myTurn)

            totalScore += when {
                opponentHandShape == myHandShape -> RoundStatus.DRAW.point
                myHandShape.winAgainst.contains(opponentHandShape.value) -> RoundStatus.WIN.point
                else -> RoundStatus.LOOSE.point
            }
            totalScore += myHandShape.point
        }

        return totalScore
    }

    fun part2(): Int {
        var totalScore = 0

        puzzleInput.forEach {
            val (opponentTurn, roundEndStatus) = it.split(" ")
            val opponentHandShape = HandShapeType.get(opponentTurn)
            val roundStatus = RoundStatus.get(roundEndStatus)

            totalScore += when(roundStatus) {
                RoundStatus.LOOSE -> HandShapeType.get(opponentHandShape.winAgainst).point
                RoundStatus.DRAW -> opponentHandShape.point
                RoundStatus.WIN -> HandShapeType.get(opponentHandShape.looseAgain).point
            }
            totalScore += roundStatus.point
        }

        return totalScore
    }

    println(part1())
    println(part2())
}
