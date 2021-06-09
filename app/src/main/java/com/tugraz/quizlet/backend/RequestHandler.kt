package com.tugraz.quizlet.backend

import androidx.annotation.VisibleForTesting
import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.DBInterface
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import io.realm.RealmList
import io.realm.mongodb.AppException
import org.bson.types.ObjectId
import java.util.logging.Logger
import kotlin.jvm.Throws

class RequestHandler(private val dBInterface: DBInterface) {
    companion object {
        val LOG: Logger = Logger.getLogger(RequestHandler::class.java.name)
        const val POINTS_FOR_RIGHT_ANSWER = 5
    }

    private var remainingQuestionForCurrentGame: ArrayList<Question> = ArrayList()
    private var highscoreForCurrentGame = -POINTS_FOR_RIGHT_ANSWER

    @Throws(Exception::class)
    fun addUser(email: String, password: String) {
        LOG.fine("Processing adding user with email=$email")
        dBInterface.addUser(email, password)
    }

    @Throws(Exception::class)
    fun loginUser(email: String, password: String): Boolean {
        LOG.fine("Processing getting user with email=$email")
        return dBInterface.loginUser(email, password)
    }

    fun addQuestion(
        category: Question_category,
        question: String,
        rightAnswer: String,
        wrongAnswers: ImmutableList<String>
    ) {
        val newQuestion = Question(
            ObjectId(),
            category,
            question,
            rightAnswer,
            null,
            getRealmListFromImmutableList(wrongAnswers)
        )
        LOG.fine("Processing getting user with email=$newQuestion")
        dBInterface.addQuestion(newQuestion)
    }

    private fun getRealmListFromImmutableList(wrongAnswers: ImmutableList<String>): RealmList<String> {
        LOG.fine("Processing getting all questions")
        val realmList = RealmList<String>()
        wrongAnswers.forEach {
            realmList.add(it)
        }
        return realmList
    }

    fun fetchAllQuestionsAsyncAndSetRemainingQuestions(callback: () -> Unit) {
        dBInterface.getAllQuestionsAsync{questions ->
            run {
                remainingQuestionForCurrentGame.addAll(questions)
                callback()
            }
        }
    }

    private fun getNextQuestionAndUpdateRemaining(): Question? {
        LOG.fine("Get Next Question")
        return remainingQuestionForCurrentGame.removeFirstOrNull()
    }

    fun getNextQuestionAndUpdateRemainingAndUpdateHighscore(): Question? {
        this.highscoreForCurrentGame += POINTS_FOR_RIGHT_ANSWER
        return getNextQuestionAndUpdateRemaining()
    }

    fun getQuestionCount() : String {
        return dBInterface.getQuestionCount()
    }

    fun getQuestionCategoryCount() : String {
        return dBInterface.getQuestionCategoryCount()
    }

    fun endCurrentGameAndReturnCurrentHighscoreAndUpdateDatabase(): Int {
        remainingQuestionForCurrentGame.clear()
        if (getHighscoreOfCurrentUser() < highscoreForCurrentGame) {
            dBInterface.updateUserHighscore(this.highscoreForCurrentGame)
        }
        LOG.fine("Ended current game with highscore: $highscoreForCurrentGame")
        return highscoreForCurrentGame
    }

    fun resetHighscoreCurrentGame() {
        LOG.finest("Current highscore was reset")
        this.highscoreForCurrentGame = -POINTS_FOR_RIGHT_ANSWER
    }

    fun getHighscoreOfCurrentUser(): Int {
        LOG.fine("DB request of highest highscore for current user")
        return dBInterface.getHighscoreOfCurrentUser()
    }

    fun getHighscoreCurrentGame(): Int {
        return this.highscoreForCurrentGame
    }

    fun getEmailOfCurrentUser(): String {
        return dBInterface.getEmailOfCurrentUser()
    }

    @VisibleForTesting
    fun setRemainingQuestionForCurrentGame(questions: ArrayList<Question>) {
        this.remainingQuestionForCurrentGame = questions
    }

    @VisibleForTesting
    fun getRemainingQuestionForCurrentGame(): ArrayList<Question> {
        return this.remainingQuestionForCurrentGame
    }

    @VisibleForTesting
    fun setHighscoreCurrentGame(highscore: Int) {
        this.highscoreForCurrentGame = highscore
    }
}
