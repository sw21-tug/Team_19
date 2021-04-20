package com.tugraz.quizlet.backend

import androidx.annotation.NonNull

class RequestHandler(private val dBInterface: DBInterface) {

    @NonNull
    fun addUser(email: String, password: String) {
        val newUser = User(email, password)
        dBInterface.addUser(newUser)
    }

    fun getUser(email: String, password: String): User? {
        return null
    }
}
