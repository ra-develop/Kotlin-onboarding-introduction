package jetbrains.kotlin.course.warmup


fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String, alphabet: String): String {
/*    return "Welcome to the game! \n" +
            "\n" +
            "Two people play this game: one chooses a word (a sequence of letters), the other guesses it. In this version, the computer chooses the word: a sequence of $wordLength letters (for example, $secretExample). The user has several attempts to guess it (the max number is $maxAttemptsCount). For each attempt, the number of complete matches (letter and position) and partial matches (letter only) is reported. \n" +
            "\n" +
            "For example, with $secretExample as the hidden word, the BCDF guess will give 1 full match (C) and 1 partial match (B)." +
            "\n" +
            "The possible symbols in the word: $alphabet"
    */

    return """
Welcome to the game! 

Two people play this game: one chooses a word (a sequence of letters), the other guesses it. In this version, the computer chooses the word: a sequence of $wordLength letters (for example, $secretExample). The user has several attempts to guess it (the max number is $maxAttemptsCount). For each attempt, the number of complete matches (letter and position) and partial matches (letter only) is reported. The possible symbols in the word: $alphabet. 

For example, with $secretExample as the hidden word, the BCDF guess will give 1 full match (C) and 1 partial match (B).

""".trimIndent()

}

fun generateSecret(wordLength: Int, alphabet: String): String {
    val secret = List(wordLength) {alphabet.random()}
    return secret.joinToString("")
}

fun countPartialMatches(secret: String, guess: String): Int {
    var guessInSecret = 0
    for (symbol in secret) {
        if (guess.any { it == symbol }) { guessInSecret++ }
    }
    var secretInGuess = 0
    for (symbol in guess) {
        if (secret.any { it == symbol }) { secretInGuess++ }
    }
    val result = minOf(guessInSecret, secretInGuess)

    return result - countExactMatches(secret, guess)
}

fun countExactMatches(secret: String, guess: String): Int {
    return secret.filterIndexed { index, symbol ->
        guess[index] == symbol
    }.length
}

fun isComplete(secret: String, guess: String): Boolean
    = secret == guess

fun isWin(complete: Boolean, attempts: Int, maxAttemptsCount: Int): Boolean =
    attempts <= maxAttemptsCount && complete
fun isLoss(complete: Boolean, attempts: Int, maxAttemptsCount: Int): Boolean =
    attempts > maxAttemptsCount && !complete

fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int) {
    var complete = false
    var attempts = 0
    while(!complete) {
        println("Please input your guess. It should be of length $wordLength.")
        val guess = safeReadLine()
        complete = isComplete(secret, guess)
        printRoundResults(secret, guess)
        attempts++
        if (isWin(complete, attempts, maxAttemptsCount)) {
            println("Congratulations! You guessed it!")
            break
        } else if (isLoss(complete, attempts, maxAttemptsCount)) {
            println("Sorry, you lost! :( My word is $secret")
            break
        }
    }
}

// = TODO("Not implemented yet")

fun printRoundResults(secret: String, guess: String) {
    val exactMatches = countExactMatches(secret, guess)
    val partialMatches = countPartialMatches(secret, guess)
    println("Your guess has $exactMatches full matches and $partialMatches partial matches.")
}

fun main() {
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"
    val alphabet = "ABCDEFGH"

    println(getGameRules(wordLength, maxAttemptsCount, secretExample, alphabet))

    val secret = generateSecret(wordLength, alphabet)

    playGame(secret, wordLength, maxAttemptsCount)
}
