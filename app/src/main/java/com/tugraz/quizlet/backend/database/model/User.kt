package com.tugraz.quizlet.backend.database.model

import java.util.*

public class User public constructor(
    private val email: String,
    private var password: String,
    private val privateQuestion: List<Question>
){
    public constructor(name: String, password: String) : this(name, password, ArrayList<Question>())
}