package com.tugraz.quizlet.backend.database.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
    @PrimaryKey var _id: String? = null,
    var highscore: Long? = null,
    var userCreated: String? = null
): RealmObject() {}
