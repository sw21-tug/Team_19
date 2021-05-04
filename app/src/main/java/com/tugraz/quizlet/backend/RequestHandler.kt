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
    }

    // TODO: add boolean for feedback?
    fun addUser(email: String, password: String) {
        LOG.fine("Processing adding user with email=$email")
        dBInterface.addUser(email, password)
    }

    @Throws(AppException::class)
    fun loginUser(email: String, password: String): Boolean {
        LOG.fine("Processing getting user with email=$email")
        return dBInterface.loginUser(email, password)
    }

    // TODO: add boolean for feedback?
    fun addQuestion(category: Question_category, question: String, rightAnswer: String, wrongAnswers: ImmutableList<String>) {
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
        val realmList = RealmList<String>()
        wrongAnswers.forEach {
            realmList.add(it)
        }
        return realmList
    }

    fun getAllQuestion(): ImmutableList<Question> {
        LOG.fine("Processing getting all questions")
        return dBInterface.getAllQuestions();
    }

    fun getAllQuestionForCategory(categoryName: String): ImmutableList<Question> {
        LOG.fine("Processing getting all questions for category=$categoryName")
        return dBInterface.getAllQuestionsForCategory(categoryName);
    }

    fun startNewGameAndReturnTheFirstQuestion(): Question? {
        return null
    }

    @VisibleForTesting
    fun setRemainingQuestionForCurrentGame(expectedQuestions: ImmutableList<Question>) {

    }

    @VisibleForTesting
    fun getHighscoreCurrentGame(): Int {
        return 0
    }

    @VisibleForTesting
    fun setHighscoreCurrentGame(score: Int) {
    }

    fun getNextQuestionAndUpdateRemainingAndUpdateHighscore(): Question? {
        return null
    }

    fun endCurrentGameAndReturnCurrentHighscoreAndUpdateDatabase(): Int {
        return 0
    }
}
