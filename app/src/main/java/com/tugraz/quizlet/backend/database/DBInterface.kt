package com.tugraz.quizlet.backend.database

import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import com.tugraz.quizlet.backend.database.model.User

interface DBInterface {
    fun addQuestion(question: Question)

    fun getAllQuestions() : MutableList<Question>?

    fun getAllQuestionsForCategory(category: Question_category) : List<Question>

    fun addUser(user: User): Boolean

    fun loginUser(email: String, password: String) : User
    fun onDestroy()
    fun onStop()
}