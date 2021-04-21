package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList

interface DBInterface {
    fun addUser(user: User)
    // TODO consider safety of password
    fun getUser(email: String) : User

    fun addQuestion(question: Question)
    fun getAllQuestionsForCategory(category: String) : ImmutableList<Question>
    fun getAllQuestions() : ImmutableList<Question>
}