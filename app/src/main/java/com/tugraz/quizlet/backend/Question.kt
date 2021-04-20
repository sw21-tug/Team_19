package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList

class Question(val category: String, val question: String, val rightAnswer: String, val wrongAnswers: ImmutableList<String>) {

}
