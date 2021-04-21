package com.example.quizlet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var login = findViewById(R.id.login) as Button
        login.setOnClickListener{
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}