package jetbrains.kotlin.course.hangman

// = TODO("Not yet implemented")

fun gameRules() {
    println(
        """
Welcome to the game!

In this game, you need to guess the word chosen by the computer.
The hidden word will appear as a sequence of underscore characters, one per letter.
You have $maxAttemptsCount attempts to guess the word.
All words are English words, consisting of $wordLength letters.
At each attempt, you should enter one letter; 
if it is present in the hidden word, all matches will be displayed.

For example, if the word CAT was chosen by the computer, _ _ _ will be displayed first,
since the word has 3 letters.
If you enter the letter A, you will see _ A _, and so on.

Good luck with the game!
        """.trimIndent()
    )
}

fun generateNewUserWord(secret: String, guess: Char, currentUserWord: String): String {
    val generatedUserWord = currentUserWord.split(" ").toMutableList()
    for (index in secret.indices) {
        if (secret[index] == guess) {
            generatedUserWord[index] = guess.toString()
        }
    }
    val newUserWord = generatedUserWord.joinToString(" ")
    if (isComplete(secret, newUserWord)) {
        println("Great! This letter is in the word! The current word is: $newUserWord")
    } else {
        println("Sorry, the secret does not contain the symbol: $guess. The current word is: $currentUserWord")
    }
    return newUserWord
}

fun isComplete(secret: String, currentUserWord: String): Boolean =
    secret == currentUserWord.replace(" ", "")

fun generateSecret(): String = words.random()

fun isCorrectInput(userInput: String): Boolean  {
    if (userInput.length != 1) {
        println("The length of your guess should be 1! Try again!")
        return false
    }
    if (!userInput.first().isLetter()) {
        println("You should input only English letters! Try again!")
        return false
    }
    return true
}

fun safeUserInput(): Char {
    var userInput = ""
    for (attempts in 0 .. maxAttemptsCount) {
        println("Please input your guess.")
        userInput = safeReadLine().uppercase()
        if (isCorrectInput(userInput)) {
            break
        }
    }
    return userInput.first()
}

fun gamePlay(secretWord: String) {

    var currentUserWord = List(wordLength) {"_"}.joinToString(" ")
    for (attempts in 0 .. maxAttemptsCount) {
        val userInput = safeUserInput()
        currentUserWord = generateNewUserWord(secretWord, userInput, currentUserWord)
    }
    if (isComplete(secretWord,currentUserWord)) {
        println("Congratulations! You guessed it!")
    } else {
        println("Sorry, you lost! My word is $secretWord")
    }

}

fun main() {
    val secretWord = generateSecret()

    gameRules()

    gamePlay(secretWord)

}
