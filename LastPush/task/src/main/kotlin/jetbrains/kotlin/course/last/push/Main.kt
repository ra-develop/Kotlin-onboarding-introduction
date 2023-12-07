package jetbrains.kotlin.course.last.push

fun fillPatternRow(patternRow: String, patternWidth: Int): String {
    if (patternRow.length > patternWidth) {
        error("patternRow.length should be < patternWidth")
    }
    return patternRow.padEnd(patternWidth, separator)
}

fun getPatternHeight(pattern: String): Int = pattern.lines().size

fun main() {
    // Write your code here
}
