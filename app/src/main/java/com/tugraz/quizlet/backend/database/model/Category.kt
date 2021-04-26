package com.tugraz.quizlet.backend.database.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import org.bson.types.ObjectId

@RealmClass(embedded = true)
open class Question_category(
    var description: String? = null,
    var name: String? = null
) :RealmObject() {
}