package com.tugraz.quizlet.database

import org.junit.Test
import org.junit.Assert.*

class QuestionDatabaseTest {
    @Test
    fun addQuestion() {
        val answers = ArrayList<String>()
        answers.add("How")
        answers.add("Who")
        answers.add("Why")
        answers.add("Because")

        val demoQuestion = Question("What?", answers, 4, Category("Super Category"))
        DBManager.addQuestion(demoQuestion)

        val allQuestions = DBManager.getAllQuestions()

        assertTrue(allQuestions.contains(demoQuestion))
    }
}