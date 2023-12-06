package jetbrains.kotlin.course.almost.done

fun trimPicture(picture: String): String = picture.trimIndent()

fun applyFilter(trimmedPictures: String, filterName: String): String {
   return when (filterName) {
       "borders" -> applyBordersFilter(trimmedPictures)
       "squared" -> applySquaredFilter(trimmedPictures)
       else -> error("Unexpected number")
   }
}

fun applyBordersFilter(trimmedPictures: String): String {
    val lines = trimmedPictures.lines()
    val maxLineLength = lines.maxBy { it.length }.length
//    val lengthToSquare = 4

    val sb = StringBuilder()
    sb.append(applyBorderLine(maxLineLength) + newLineSymbol)
    for (line in lines) {
        sb.append("$borderSymbol$separator")
        if (line.length < maxLineLength) {
            sb.append(line.padEnd(maxLineLength, separator))
        } else {
            sb.append(line)
        }
        sb.append("$separator$borderSymbol$newLineSymbol")
    }
    sb.append(applyBorderLine(maxLineLength))
    return sb.toString()
}

fun applySquaredFilter(trimmedPictures: String): String {
    val lines = trimmedPictures.lines()
    val maxLineLength = lines.maxBy { it.length }.length
//    val lengthToSquare = 4

    val sb = StringBuilder()
    sb.append(applyBorderLine(maxLineLength))
    sb.append(applyBorderLine(maxLineLength) + newLineSymbol)
    for (i in 0 .. 1) {
        for (line in lines) {
            sb.append("$borderSymbol$separator")
            if (line.length < maxLineLength) {
                sb.append(line.padEnd(maxLineLength, separator))
                sb.append("$separator$borderSymbol")
                sb.append("$borderSymbol$separator")
                sb.append(line.padEnd(maxLineLength, separator))
            } else {
                sb.append(line)
                sb.append("$separator$borderSymbol")
                sb.append("$borderSymbol$separator")
                sb.append(line)
            }
            sb.append("$separator$borderSymbol$newLineSymbol")
        }
        sb.append(applyBorderLine(maxLineLength))
        sb.append(applyBorderLine(maxLineLength) + newLineSymbol)
    }

    return sb.toString()
}

fun applyBorderLine(maxLineLength: Int): String {
    val lengthToSquare = 4
    return borderSymbol.toString().repeat(maxLineLength + lengthToSquare)
}

fun main() {
    // Write your solution in this file
}
