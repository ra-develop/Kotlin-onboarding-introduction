package jetbrains.kotlin.course.warmup


fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String): String {
    return "Welcome to the game! \n" +
            "\n" +
            "Two people play this game: one chooses a word (a sequence of letters), the other guesses it. In this version, the computer chooses the word: a sequence of $wordLength letters (for example, $secretExample). The user has several attempts to guess it (the max number is $maxAttemptsCount). For each attempt, the number of complete matches (letter and position) and partial matches (letter only) is reported. \n" +
            "\n" +
            "For example, with $secretExample as the hidden word, the BCDF guess will give 1 full match (C) and 1 partial match (B)."

}

fun generateSecret(): String {
    return "ABCD"
}

fun countPartialMatches(secret: String, guess: String): Int = TODO("Not implemented yet")

fun countExactMatches(secret: String, guess: String): Int = TODO("Not implemented yet")

fun main() {
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"

    println("Welcome to the game! \n" +
            "\n" +
            "Two people play this game: one chooses a word (a sequence of letters), the other guesses it. In this version, the computer chooses the word: a sequence of $wordLength letters (for example, $secretExample). The user has several attempts to guess it (the max number is $maxAttemptsCount). For each attempt, the number of complete matches (letter and position) and partial matches (letter only) is reported. \n" +
            "\n" +
            "For example, with $secretExample as the hidden word, the BCDF guess will give 1 full match (C) and 1 partial match (B).")


}
