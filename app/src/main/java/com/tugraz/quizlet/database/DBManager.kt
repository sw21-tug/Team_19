package com.tugraz.quizlet.database

public object DBManager : DBInterface {
    init {
        //DB connection here
    }

    public override fun addQuestion(question: Question) {

    }

    public override fun getAllQuestions() : List<Question> {
        return ArrayList<Question>()
    }

    public override fun getAllQuestionsForCategory(category: Category) : List<Question> {
        return ArrayList<Question>()
    }

    public override fun addUser(user: User) {

    }

    public override fun getUser(email: String, password: String) : User {
        return User("real@fake.user", "123 *hashed*")
    }
}