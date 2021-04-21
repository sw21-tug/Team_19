package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList

interface DBInterface {
    fun addUser(user: User)
    fun getUser(email: String, password: String) : User

    fun addQuestion(question: Question)
    fun getAllQuestionsForCategory(category: String) : ImmutableList<Question>
    fun getAllQuestions() : ImmutableList<Question>
}