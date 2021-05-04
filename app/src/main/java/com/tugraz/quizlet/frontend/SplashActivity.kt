package com.tugraz.quizlet.frontend

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.tugraz.quizlet.R
import com.tugraz.quizlet.backend.RequestHandler
import com.tugraz.quizlet.backend.database.DBManager
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration


class SplashActivity : AppCompatActivity(){

    companion object {
        lateinit var requestHandler: RequestHandler
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Realm.init(this)
        val quizletApp = App(
            AppConfiguration.Builder("quizlet-sxmwi")
                .build())

        val dbManager = DBManager(quizletApp)
        // TODO: make requestHandler Singleton?
        requestHandler = RequestHandler(dbManager)


        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

}