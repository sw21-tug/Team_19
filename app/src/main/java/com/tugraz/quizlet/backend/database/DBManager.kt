package com.tugraz.quizlet.backend.database

import android.content.ContentValues.TAG
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.tugraz.quizlet.BuildConfig
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import com.tugraz.quizlet.backend.database.model.User
import com.tugraz.quizlet.quizletApp
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.Credentials.anonymous
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.types.ObjectId
import java.util.*

public object DBManager : DBInterface {
    private var user: io.realm.mongodb.User? = null
    private var userRealm: Realm? = null

    init {
        // testFunctionality
        // register new user

        /*
        quizletApp.emailPassword.registerUserAsync("testuser", "testpassword") {
            if (!it.isSuccess) {
                Log.e("TAG", "Error: ${it.error}")
            } else {
                Log.i("TAG", "Successfully registered user.")
            }
        }

         */

        val creds = Credentials.emailPassword("testuser", "testpassword")
        quizletApp.loginAsync(creds) {
            // re-enable the buttons after user login returns a result
            if (!it.isSuccess) {
                Log.e("TAG", "Error: ${it.error}")
            } else {
                Log.i("TAG", "Successfully logged in user.")
            }
        }


        var list: RealmList<String> = RealmList()
        list.add("1921")
        list.add("1923")
        list.add("1950")
        list.add("1930")
        var category = Question_category( "für die gerti" ,"Gertis Geburtstag")
        var question = Question(
            ObjectId(),
            list, category,  "Gertis Geburtstag" ,"Wann ist Gerti geboren?",1, )
        addQuestion(question)
    }
    public override fun addQuestion(question: Question) {
        user = quizletApp.currentUser()
        val config = SyncConfiguration.Builder(user!!, "user=${user!!.id}")
            .build()

        Realm.getInstanceAsync(config, object: Realm.Callback() {
            override fun onSuccess(realm: Realm) {
                // since this realm should live exactly as long as this activity, assign the realm to a member variable
                this@DBManager.userRealm = realm
            }
        })
        val collection = userRealm?.executeTransaction { r: Realm ->
            r.insert(question)
            Log.v("INSERT", "did something")
        }
        System.out.println(collection)
    }

    public override fun getAllQuestions() : List<Question> {
        val questionList:List<Question> = ArrayList()
        return  questionList
    }

    public override fun getAllQuestionsForCategory(category: Question_category) : List<Question> {
        return ArrayList<Question>()
    }

    public override fun addUser(user: User) {

    }

    public override fun getUser(email: String, password: String) : User {
        return com.tugraz.quizlet.backend.database.model.User("real@fake.user", "123")
    }
    override fun onDestroy() {
        userRealm?.close()
    }

    override fun onStop() {
        userRealm?.close()
    }

}