package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList

class RequestHandler(private val dBInterface: DBInterface) {

    // TODO: add boolean for feedback?
    fun addUser(email: String, password: String) {
        val newUser = User(email, password)
        dBInterface.addUser(newUser)
    }

    fun getUser(email: String, password: String): User {
        return dBInterface.getUser(email, password)
    }

    // TODO: add boolean for feedback?
    fun addQuestion(category: String, question: String, rightAnswer: String, wrongAnswers: ImmutableList<String>) {
        val newQuestion = Question(category, question, rightAnswer, wrongAnswers)
        dBInterface.addQuestion(newQuestion)
    }

    fun getAllQuestion(): ImmutableList<Question> {
        return dBInterface.getAllQuestions();
    }

    fun getAllQuestionForCategory(category: String): ImmutableList<Question> {
        return dBInterface.getAllQuestionsForCategory(category);
    }
}
