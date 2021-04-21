package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList
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

    fun getUser(email: String): User {
        LOG.fine("Processing getting user with email=$email")
        return dBInterface.getUser(email)
    }

    // TODO: add boolean for feedback?
    fun addQuestion(category: String, question: String, rightAnswer: String, wrongAnswers: ImmutableList<String>) {
        val newQuestion = Question(category, question, rightAnswer, wrongAnswers)
        LOG.fine("Processing getting user with email=$newQuestion")
        dBInterface.addQuestion(newQuestion)
    }

    fun getAllQuestion(): ImmutableList<Question> {
        LOG.fine("Processing getting all questions")
        return dBInterface.getAllQuestions();
    }

    fun getAllQuestionForCategory(category: String): ImmutableList<Question> {
        LOG.fine("Processing getting all questions for category=$category")
        return dBInterface.getAllQuestionsForCategory(category);
    }
}
