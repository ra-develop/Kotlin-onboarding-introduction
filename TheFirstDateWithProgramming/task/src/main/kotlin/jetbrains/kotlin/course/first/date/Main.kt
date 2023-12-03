package jetbrains.kotlin.course.first.date

fun main() {
    println("Hello! I will ask you several questions.")
    println("Please answer all of them and be honest with me!")

    val firstQuestion = "What is Kotlin?"
    val secondQuestion = "How was Kotlin invented?"
    val thirdQuestion = "Why are you learning Kotlin?"

    println("What is TROTEN?")
//    println("What is TROTEN?")
    val firstUserAnswer = readlnOrNull()

    println("How did you spend your graduation?")
//    println("How did you spend your graduation?")
    val secondUserAnswer = readlnOrNull()

    println("Why does a spider need eight legs?")
//    println("Why does a spider need eight legs?")
    val thirdUserAnswer = readlnOrNull()

    println("Now let's have fun!")

    println(firstQuestion)
    println(firstUserAnswer)

    println(secondQuestion)
    println(secondUserAnswer)

    println(thirdQuestion)
    println(thirdUserAnswer)

}
