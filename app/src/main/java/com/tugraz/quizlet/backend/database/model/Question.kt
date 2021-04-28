package com.tugraz.quizlet.backend.database.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId
import java.security.InvalidParameterException

open class Question(
    @PrimaryKey var _id: ObjectId = ObjectId(),
    @Required
    var answers: RealmList<String> = RealmList(),
    var category: Question_category? = null,
    var categoryName: String? = null,
    var question: String? = null,
    var rightAnswer: Long? = null
):RealmObject() {

    init {
        if(answers?.size != 4)
            throw InvalidParameterException("Invalid number of answers. Must be 4")
    }
}