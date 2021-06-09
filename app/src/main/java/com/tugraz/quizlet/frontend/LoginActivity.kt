package com.tugraz.quizlet.frontend

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tugraz.quizlet.R
import io.realm.mongodb.AppException
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // TODO: Set new Text for lang support
        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            val email = findViewById<EditText>(R.id.editTextTextEmailAddress).text
            val password = findViewById<EditText>(R.id.editTextTextPassword).text
            if(login(email.toString(), password.toString())){
                startActivity(intent)
                finish()
            }
        }
        val register = findViewById<Button>(R.id.register)
        register.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            val email = findViewById<EditText>(R.id.editTextTextEmailAddress).text
            val password = findViewById<EditText>(R.id.editTextTextPassword).text
            if(register(email.toString(), password.toString())){
                startActivity(intent)
                finish()
            }
        }
    }

    private fun login(email: String, password: String): Boolean {
        try {
            SplashActivity.requestHandler.loginUser(email, password)
            return true

        } catch (exception: Exception) {
            val toastText = getString(R.string.wrong_email_password)
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            return false

        }
    }

    private fun register(email: String, password: String): Boolean{
        try {
            SplashActivity.requestHandler.addUser(email, password)
            return true
        } catch (exception: Exception) {
            val toastText = getString(R.string.invalid_email_password)
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            return false
        }
    }
}