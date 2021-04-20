package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList

interface DBInterface {

    /*
    public fun getAllQuestions() : List<Question> {
        return ArrayList<Question>()
    }

    public fun getAllQuestionsForCategory(category: Category) : List<Question> {
        return ArrayList<Question>()
    }

    public fun getUser(email: String, password: String) : User {
        return User("real@fake.user", "123 *hashed*")
    }*/

    fun addUser(user: User)
    fun getUser(email: String, password: String) : User

    fun addQuestion(question: Question)
    fun getAllQuestionsForCategory(category: String) : ImmutableList<Question>
    fun getAllQuestions() : ImmutableList<Question>
}