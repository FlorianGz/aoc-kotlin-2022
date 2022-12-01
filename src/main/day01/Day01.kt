import java.io.File

fun main() {

    fun getCalories(): List<Int> {
        val puzzleInput = readInput("main/day01/Day01")

        val calories = arrayListOf<Int>()

        puzzleInput.fold(initial = 0) { currentValue, item ->
            if(item.isNotBlank()) {
                currentValue + item.toInt()
            } else {
                calories.add(currentValue)
                0
            }
        }

        return calories.sortedDescending()
    }


    fun part1(): Int {
        val calories = getCalories()
        return calories.first()
    }

    fun part2(): Int {
        val calories = getCalories()
        return calories.take(n = 3).sum()
    }

    println(part1())
    println(part2())
}
