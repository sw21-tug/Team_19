package com.tugraz.quizlet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tugraz.quizlet.backend.database.DBManager
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import io.realm.Realm
import io.realm.RealmList
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import org.bson.types.ObjectId

lateinit var quizletApp: App
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)
        quizletApp = App(
            AppConfiguration.Builder(BuildConfig.MONGODB_REALM_APP_ID)
                .build())

        var database = DBManager
    }
}