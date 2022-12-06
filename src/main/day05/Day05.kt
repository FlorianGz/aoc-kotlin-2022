package main.day05

import kotlinx.coroutines.*
import readInput

fun main() = runBlocking {

    suspend fun part1(): String {
        return getCode(reversed = true)
    }

    suspend fun part2(): String {
        return getCode(reversed = false)
    }

    println(part1())
    println(part2())
}

suspend fun getCode(reversed: Boolean): String = runBlocking  {
    val cratesInput = readInput("main/day05/day05")

    val cratesMapDeferred = async { getInitialCratesMap(cratesInput) }
    val instructionsDeferred = async { getInstructions(cratesInput) }

    val cratesMap = cratesMapDeferred.await().toMutableMap()
    val instructions = instructionsDeferred.await()

    instructions.forEach { instruction ->
        cratesMap[instruction.from]?.let {
            val index = if(it.length - instruction.quantity < 0) 0 else it.length - instruction.quantity
            val toExtract = it.substring(index, it.length)
            cratesMap[instruction.from] = it.substring(0, index)
            cratesMap[instruction.to] = cratesMap[instruction.to] + if (reversed) toExtract.reversed() else toExtract
        }
    }

    cratesMap.toSortedMap().mapNotNull { it.value.lastOrNull() }.joinToString("")
}

fun getInitialCratesMap(cratesInput: List<String>): Map<Int, String> {
    val crateRegexp = Regex("\\[([A-Z])\\]\\s*")
    val cratesMap = mutableMapOf<Int, String>()
    cratesInput.takeWhile { it.isNotBlank() }.map { it.chunked(4) }.map { items ->
        items.forEachIndexed { index, input ->
            if(crateRegexp.matches(input)) {
                val matches = crateRegexp.find(input)!!.groupValues[1]
                if(cratesMap[index] == null) cratesMap[index] = matches
                else cratesMap[index] = matches + cratesMap[index]
            }
        }
    }
    return cratesMap
}

fun getInstructions(cratesInput: List<String>): List<Instructions> {
    val instructionRegexp = Regex("move (\\d+) from (\\d+) to (\\d+)")
    return cratesInput.mapNotNull { instruction ->
        val match = instructionRegexp.find(instruction)
        match?.let { matchResult ->
            val (quantity, from, to) = matchResult.destructured.toList().map { it.toInt() }
            Instructions(quantity = quantity, from = from - 1, to = to - 1)
        }
    }
}
