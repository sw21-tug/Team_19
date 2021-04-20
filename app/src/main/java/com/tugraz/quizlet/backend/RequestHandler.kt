package com.tugraz.quizlet.backend

import androidx.annotation.NonNull
import com.google.common.collect.ImmutableList

class RequestHandler(private val dBInterface: DBInterface) {

    @NonNull
    fun addUser(email: String, password: String) {
        val newUser = User(email, password)
        dBInterface.addUser(newUser)
    }

    fun getUser(email: String, password: String): User {
        return dBInterface.getUser(email, password)
    }

    fun addQuestion(category: String, question: String, rightAnswer: String, wrongAnswers: ImmutableList<String>) {

    }
}
