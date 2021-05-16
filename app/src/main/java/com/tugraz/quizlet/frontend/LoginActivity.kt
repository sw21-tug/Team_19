package com.tugraz.quizlet.frontend

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tugraz.quizlet.R
import io.realm.mongodb.AppException


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // TODO: Set new Text for lang support

//        var locales = resources.configuration.locales
//        if (!locales.isEmpty && locales.get(0).language != Locale.getDefault().language) {
//            val email = findViewById<TextView>(R.id.text_view_email)
//            val password = findViewById<TextView>(R.id.text_view_password)
//            email.text = resources.getString(R.string.app_login_email_description)
//            password.text = resources.getString(R.string.app_login_pw_description)
//        }


        var login = findViewById<Button>(R.id.login)
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