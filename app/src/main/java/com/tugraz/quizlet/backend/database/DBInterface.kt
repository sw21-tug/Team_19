package com.tugraz.quizlet.backend.database

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.model.Question

interface DBInterface {
    fun addQuestion(question: Question)

    fun getAllQuestionsAsync(callback: (ImmutableList<Question>) -> Unit)

    fun getAllQuestionsForCategory(categoryName: String) : ImmutableList<Question>

    fun addUser(email: String, password: String): Boolean

    fun loginUser(email: String, password: String): Boolean

    fun getHighscoreOfCurrentUser(): Int

    fun updateUserHighscore(newHighscore: Int)

    fun getEmailOfCurrentUser(): String
}