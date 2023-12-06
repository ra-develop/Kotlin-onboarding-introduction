package jetbrains.kotlin.course.almost.done

fun trimPicture(picture: String): String = picture.trimIndent()

fun applyFilter(trimmedPictures: String, filterName: String): String {
   return when (filterName) {
       "borders" -> applyBordersFilter(trimmedPictures)
       "squared" -> applySquaredFilter(trimmedPictures)
       else -> error("Unexpected number")
   }
}

fun applyBordersFilter(trimmedPictures: String): String = TODO("Not yet implemented")

fun applySquaredFilter(trimmedPictures: String): String = TODO("Not yet implemented")

fun main() {
    // Write your solution in this file
}
