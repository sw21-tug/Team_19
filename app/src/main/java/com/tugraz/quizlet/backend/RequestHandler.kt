package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.DBInterface
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import com.tugraz.quizlet.backend.database.model.User
import io.realm.RealmList
import org.bson.types.ObjectId
import java.util.logging.Logger

class RequestHandler(private val dBInterface: DBInterface) {
    companion object {
        val LOG: Logger = Logger.getLogger(RequestHandler::class.java.name)
    }

    // TODO: add boolean for feedback?
    fun addUser(email: String, password: String) {
        LOG.fine("Processing adding user with email=$email")
        val newUser = User(email, password)
        dBInterface.addUser(newUser)
    }

    fun loginUser(email: String, password: String): User {
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
}
