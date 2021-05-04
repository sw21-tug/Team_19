package com.tugraz.quizlet.backend.database

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.User

interface DBInterface {
    fun addQuestion(question: Question)

    fun getAllQuestions() : ImmutableList<Question>

    fun getAllQuestionsForCategory(categoryName: String) : ImmutableList<Question>

    fun addUser(user: User): Boolean

    fun loginUser(email: String, password: String) : User
    fun onDestroy()
    fun onStop()
}