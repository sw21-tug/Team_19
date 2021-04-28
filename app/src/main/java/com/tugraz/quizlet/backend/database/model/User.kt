package com.tugraz.quizlet.backend.database.model

import java.util.*

public class User public constructor(
    public val email: String?,
    public var password: String?,
    public val privateQuestion: List<Question>?
){
    public constructor(name: String, password: String) : this(name, password, ArrayList<Question>())
}