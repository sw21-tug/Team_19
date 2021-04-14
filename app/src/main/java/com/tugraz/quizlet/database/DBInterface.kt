package com.tugraz.quizlet.database

interface DBInterface {
    fun addQuestion(question: Question)

    fun getAllQuestions() : List<Question>

    fun getAllQuestionsForCategory(category: Category) : List<Question>

    fun addUser(user: User)

    fun getUser(email: String, password: String) : User
}