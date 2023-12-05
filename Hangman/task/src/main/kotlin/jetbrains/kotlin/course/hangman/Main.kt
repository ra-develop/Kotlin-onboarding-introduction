package jetbrains.kotlin.course.hangman

// = TODO("Not yet implemented")
fun generateNewUserWord(secret: String, guess: Char, currentUserWord: String): String {
    val newUserWord = currentUserWord.split(" ").toMutableList()
    for (index in secret.indices) {
        if (secret[index] == guess) {
            newUserWord[index] = guess.toString()
        }
    }
    return newUserWord.joinToString(" ")
}

fun isComplete(secret: String, currentUserWord: String): Boolean =
    secret == currentUserWord

fun main() {
    // Write your code here
}
