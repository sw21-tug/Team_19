package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList

class Question(val category: String, val question: String, val rightAnswer: String, val wrongAnswers: ImmutableList<String>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (category != other.category) return false
        if (question != other.question) return false
        if (rightAnswer != other.rightAnswer) return false
        if (wrongAnswers != other.wrongAnswers) return false

        return true
    }

    override fun hashCode(): Int {
        var result = category.hashCode()
        result = 31 * result + question.hashCode()
        result = 31 * result + rightAnswer.hashCode()
        result = 31 * result + wrongAnswers.hashCode()
        return result
    }

    override fun toString(): String {
        return "Question(category='$category', question='$question', rightAnswer='$rightAnswer', wrongAnswers=$wrongAnswers)"
    }


}
