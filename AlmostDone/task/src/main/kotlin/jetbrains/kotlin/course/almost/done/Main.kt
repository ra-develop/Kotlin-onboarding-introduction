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

fun safeReadLine(): String = readlnOrNull() ?: error("Find null value")

fun chooseFilter(): String {
    println("Please choose the filter: 'borders' or 'squared'.")
    var filter = ""
    while(true) {
        filter = safeReadLine()
        if (isCorrectFilter(filter)) {
            break
        }
        println("Please input 'borders' or 'squared'")
    }
    return filter
}

fun isCorrectFilter(filter: String): Boolean {
    return when (filter) {
        "borders", "squared" -> true
        else -> false
    }
}

fun choosePicture(): String {
    while (true) {
        println("Please, choose a picture. The possible options: ${allPictures().joinToString(", ")}")
        val pictureName = readlnOrNull()
        pictureName?.let { pictName ->
            if (allPictures().contains(pictName)) {
                val picture = getPictureByName(pictName)
                picture?.let {
                    return@choosePicture it
                }
            }
        }
    }
}

fun getPicture(): String {
    println("Do you want to use a pre-defined picture or use a custom one? Please, input 'yes' for a pre-defined image or 'no' for a custom one")
    var picture = ""
    while (true) {
        val userAnswer = safeReadLine()
        when (userAnswer) {
            "yes" -> {
                picture = choosePicture()
                break
            }
            "no" -> {
                picture = safeReadLine()
                break
            }
            else -> {
                println("Please, input 'yes' or 'no'")
            }
        }
    }
    return picture
}

fun photoshop() {
    val picture = trimPicture(getPicture())
    val filter = chooseFilter()
    println("The old image:")
    println(picture)
    println("The transformed picture:")
    println(applyFilter(picture, filter))
}

fun main() {
    photoshop()
}
