package com.tugraz.quizlet.backend.database.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId
import java.security.InvalidParameterException

open class Question(
@PrimaryKey var _id: ObjectId = ObjectId(),
var category: Question_category? = null,
var question: String? = null,
var rightAnswer: String? = null,
var userCreated: String? = null,
@Required
var wrongAnswers: RealmList<String> = RealmList()
): RealmObject() {
    init {
        if(wrongAnswers.size > 3)
            throw InvalidParameterException("Invalid number of wrong answers. Must be 3")
    }

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
        var result = _id.hashCode()
        result = 31 * result + (category?.hashCode() ?: 0)
        result = 31 * result + (question?.hashCode() ?: 0)
        result = 31 * result + (rightAnswer?.hashCode() ?: 0)
        result = 31 * result + wrongAnswers.hashCode()
        return result
    }

    override fun toString(): String {
        return "Question(_id=$_id, category=$category, question=$question, rightAnswer=$rightAnswer, wrongAnswers=$wrongAnswers)"
    }


}