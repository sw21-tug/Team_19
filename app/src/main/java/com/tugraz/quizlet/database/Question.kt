package com.tugraz.quizlet.database

import java.security.InvalidParameterException

public class Question public constructor(
    private val text: String,
    private val answers: List<String>,
    private val rightAnswer: Int,
    private val category: Category
) {

    init {
        if(answers.count() != 4)
            throw InvalidParameterException("Invalid number of answers. Must be 4")
    }
}