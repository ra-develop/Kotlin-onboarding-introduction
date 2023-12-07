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
    var filledPattern = ""
    patternLines.forEachIndexed { index, line ->
        filledPattern += fillPatternRow(line, patternWidth) + newLineSymbol
    }
    return filledPattern.removeSuffix(newLineSymbol)
}
fun getPatternHeight(pattern: String): Int = pattern.lines().size

fun canvasGenerator(pattern: String, width: Int, height: Int): String {
    val filledPattern = fillPattern(pattern)
    var canvas = repeatHorizontally(filledPattern, width)
    for (i in 2 .. height) {
        canvas += repeatHorizontally(dropTopFromLine(filledPattern), width)
    }
    return canvas
}

fun dropTopFromLine(pattern: String): String {
    val patternLines = pattern.lines()
    if (patternLines.size == 1) {
        return pattern
    }
    return patternLines.drop(1).joinToString(newLineSymbol).removeSuffix(newLineSymbol)
}

fun repeatHorizontally(pattern: String, repeatNumber: Int): String {
    val lines = pattern.lines()
    val sb = StringBuilder()
    for (line in lines) {
        sb.append(newLineSymbol)
        for (i in 1 .. repeatNumber) {
            sb.append(line)
        }
    }
    return sb.toString()
}

fun main() {
    // Write your code here
}
