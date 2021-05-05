package com.tugraz.quizlet.backend.database.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class Question_category(
    var description: String? = null,
    var name: String? = null
) :RealmObject() {
    override fun toString(): String {
        return "Question_category(description=$description, name=$name)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question_category

        if (description != other.description) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = description?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }


}