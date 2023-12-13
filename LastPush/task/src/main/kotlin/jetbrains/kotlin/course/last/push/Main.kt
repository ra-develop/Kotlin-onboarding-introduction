package jetbrains.kotlin.course.last.push

fun fillPatternRow(patternRow: String, patternWidth: Int): String {
    if (patternRow.length > patternWidth) {
        error("patternRow.length should be < patternWidth")
    }
    return patternRow.padEnd(patternWidth, separator)
}

fun fillPattern(pattern: String): String {
    val patternLines = pattern.lines()
    val patternWidth = getPatternWidth(pattern)
    val filledPatternLines = mutableListOf<String>()
    patternLines.forEachIndexed { index, line ->
        filledPatternLines.add(fillPatternRow(line, patternWidth))
    }
    return filledPatternLines.joinToString(newLineSymbol).removePrefix(newLineSymbol)
}

fun getPatternHeight(pattern: String): Int = pattern.lines().size

fun dropTopFromLine(pattern: String): String {
    val patternLines = pattern.lines()
    if (patternLines.size > 1) {
        return patternLines.drop(1).joinToString(newLineSymbol)
    }
    return pattern
}

fun repeatHorizontally(pattern: String, repeatNumber: Int): String {
    val lines = pattern.lines()
    val sb = StringBuilder()
    for (line in lines) {
        for (i in 1..repeatNumber) {
            sb.append(line)
        }
        sb.appendLine()
    }
    return sb.toString()
}

fun canvasGenerator(pattern: String, width: Int, height: Int): String {
    val filledPattern = fillPattern(pattern)
    var canvas = repeatHorizontally(filledPattern, width)
    for (i in 2..height) {
        canvas += repeatHorizontally(dropTopFromLine(filledPattern), width)
    }
    return canvas
}

fun repeatHorizontallyWithGaps(pattern: String, repeatNumber: Int, level: Int): String {
    val patternWidth = getPatternWidth(pattern)
    val lines = pattern.lines()
    val sb = StringBuilder()
    for (line in lines) {
        for (i in 1 .. repeatNumber) {
            if (level % 2 == 0) {
                if (i % 2 == 0) {
                    sb.append(line)
                } else {
                    sb.append(separator.toString().repeat(patternWidth))
                }
            } else {
                if (i % 2 == 0) {
                    sb.append(separator.toString().repeat(patternWidth))
                } else {
                    sb.append(line)
                }
            }
        }
        sb.appendLine()
    }
    return sb.toString()
}

fun canvasWithGapsGenerator(pattern: String, width: Int, height: Int): String {
    val filledPattern = fillPattern(pattern)
    var canvas = ""
    for (i in 1 .. height ) {
        canvas += repeatHorizontallyWithGaps(filledPattern, width, if (height == 2) { 1 } else { i })
    }
    return canvas
}

fun choosePattern(): String {
    while (true) {
        println("Please choose a pattern. The possible options: ${allPatterns().joinToString(", ")}")
        val patternName = readlnOrNull()
        patternName?.let { it ->
            if (allPatterns().contains(it)) {
                val pattern = getPatternByName(it)
                pattern?.let {
                    return@choosePattern it
                }
            }
        }
    }
}

fun safeReadLine(): String = readlnOrNull() ?: error("Your input is incorrect, sorry")

fun getPattern(): String {
    println("Do you want to use a pre-defined pattern or a custom one? Please input 'yes' for a pre-defined pattern or 'no' for a custom one")
    var pattern = ""
    while (true) {
        val userAnswer = safeReadLine()
        when (userAnswer) {
            "yes" -> {
                pattern = choosePattern()
                break
            }
            "no" -> {
                println("Please, input a custom picture")
                pattern = safeReadLine()
                break
            }
            else -> println("Please input 'yes' or 'no'")
        }
    }
    return pattern
}

fun chooseGenerator(): String {
    println("Please choose the generator: 'canvas' or 'canvasGaps'.")
    var generatorName = ""
    while (true) {
        generatorName = safeReadLine()
        when (generatorName) {
            "canvas", "canvasGaps" -> break
            else -> {
                println("Unsupported generator: <generatorName>\n")
                println("Please, input 'canvas' or 'canvasGaps'")
            }
        }
    }
    return generatorName
}

fun applyGenerator(pattern: String, generatorName: String, width: Int, height: Int): String {
    return if (generatorName == "canvas") {
        canvasGenerator(pattern, width, height)
    } else {
        canvasWithGapsGenerator(pattern, width, height)
    }
}



fun main() {
    val pattern = getPattern()
    val generator = chooseGenerator()
    println("Please input the width of the resulting picture:")
    val width = readln().toInt()
    println("Please input the height of the resulting picture:")
    val height = readln().toInt()
    println("The pattern:")
    println(pattern)
    println("The generated image:")
    println(applyGenerator(pattern, generator, width, height))
}
