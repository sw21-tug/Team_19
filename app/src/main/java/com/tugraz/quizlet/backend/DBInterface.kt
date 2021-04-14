package com.tugraz.quizlet.backend

interface DBInterface {

    /*public fun addQuestions(question: Question) {

    }

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
}