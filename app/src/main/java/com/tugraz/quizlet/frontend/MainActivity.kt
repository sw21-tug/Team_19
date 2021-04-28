package com.tugraz.quizlet.frontend

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tugraz.quizlet.R
import com.tugraz.quizlet.backend.database.DBManager
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration


lateinit var quizletApp: App
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)
        quizletApp = App(
            AppConfiguration.Builder("quizlet-sxmwi")
                .build())

        //var database = DBManager

        val login = findViewById(R.id.login) as Button
        login.setOnClickListener{
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}