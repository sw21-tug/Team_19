package com.tugraz.quizlet.backend.database.model
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId;
open class User(
    @PrimaryKey var _id: ObjectId? = null,
    var highscore: Long? = null,
    var userCreated: String? = null
): RealmObject() {}