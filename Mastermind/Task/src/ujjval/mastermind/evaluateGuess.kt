package ujjval.mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {

    val position = getGuessInRightDirection(secret, guess)
    val letters = getGuessWrongDirection(secret, guess)

    return Evaluation(position, letters)
}

/***
 *  Get guess wrong letters
 */
fun getGuessWrongDirection(secret: String, guess: String): Int {

    var letters = 0
    var newSecret = ""
    var newGuess = ""

    for (i in secret.indices) {

        if (secret[i] != guess[i]) {
            newSecret += secret[i]
            newGuess += guess[i]
        }

    }

    val evaluatedChars = mutableListOf<Char>()

    if (!newSecret.isBlank()) {

        for (element in guess) {

            if (!evaluatedChars.contains(element)) {

                val howManyInSecret = countHowManyChar(newSecret, element)
                val howManyInGuess = countHowManyChar(newGuess, element)

                if (howManyInSecret == howManyInGuess|| howManyInGuess < howManyInSecret)
                    letters += howManyInGuess
                else
                    letters += howManyInSecret

                evaluatedChars.add(element)
            }
        }
    }
//    println("guess wrong direction $letters")
    return letters;
}


fun countHowManyChar(s: String, letter: Char): Int {
    var howMany = 0
    for (element in s) {
        if (element == letter)
            howMany++

    }
//    println("guess howmany$howMany")
    return howMany
}


/***
 *  Get guess is right
 */

fun getGuessInRightDirection(secret: String, guess: String): Int {

    var position = 0

    for (i in secret.indices) {

        if (secret[i] == guess[i])
            position++
    }
//    println("guess in right direction $position")

    return position  // correct guessed letter count from right direction
}