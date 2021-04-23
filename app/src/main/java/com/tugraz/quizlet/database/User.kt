package com.tugraz.quizlet.database

public class User public constructor(
    private val email: String,
    private var password: String,
    private val privateQuestion: List<Question>
){
    public constructor(name: String, password: String) : this(name, password, ArrayList<Question>())
}