package com.tugraz.quizlet.frontend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.tugraz.quizlet.R
import com.tugraz.quizlet.frontend.StartActivity
import io.realm.mongodb.AppException


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById(R.id.login) as Button
        login.setOnClickListener{
            val intent = Intent(this, StartActivity::class.java)
            val email = findViewById<EditText>(R.id.editTextTextEmailAddress).text
            val password = findViewById<EditText>(R.id.editTextTextPassword).text
            login(email.toString(), password.toString())
            startActivity(intent)
            finish()
        }
    }

    private fun login(email: String, password: String) {
        try {
            SplashActivity.requestHandler.loginUser(email, password)
        } catch (exception: AppException) {
            Toast.makeText(this, "Wrong email and password!", Toast.LENGTH_SHORT).show()
        }
    }
}