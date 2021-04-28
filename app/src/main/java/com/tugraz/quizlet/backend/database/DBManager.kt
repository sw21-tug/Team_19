package com.tugraz.quizlet.backend.database

import android.util.Log
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import com.tugraz.quizlet.backend.database.model.User
import com.tugraz.quizlet.frontend.quizletApp
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.mongodb.AppException
import io.realm.mongodb.Credentials
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.types.ObjectId
import java.util.*

public object DBManager : DBInterface {
    private var user: io.realm.mongodb.User? = null
    private var userRealm: Realm? = null

    init {
        // testFunctionality - how to use the db manager
        loginUser("testuser", "testpassword")
        var list: RealmList<String> = RealmList()
        list.add("1921")
        list.add("1923")
        list.add("1950")
        list.add("1930")
        var category = Question_category( "für die gerti" ,"Gertis Geburtstag")
        var question = Question(
            ObjectId(),
            list, category,  "Gertis Geburtstag3" ,"Wann ist Gerti geboren?",1, )
        addQuestion(question)

        Thread.sleep(100)
        val questions = getAllQuestions();
    }

    override fun addQuestion(question: Question) {
        user = quizletApp.currentUser()
        val config = SyncConfiguration.Builder(user!!, ObjectId(user!!.id))
            .allowWritesOnUiThread(true)
            .build()

        userRealm = Realm.getInstance(config)
        userRealm?.executeTransactionAsync() { transactionRealm ->
            transactionRealm.insert(question)
            Log.e("INSERT", "did something")
        }
    }

    override fun getAllQuestions() : MutableList<Question>? {
        user = quizletApp.currentUser()
        val config = SyncConfiguration.Builder(user!!, ObjectId(user!!.id) )
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        var results: RealmResults<Question>? = null
        userRealm = Realm.getInstance(config)
        userRealm?.executeTransaction { transactionRealm ->
            results = transactionRealm.where(Question::class.java)?.findAll() as RealmResults<Question>;
        }

        return results?.subList(0, results!!.size)
    }


    override fun getAllQuestionsForCategory(category: Question_category) : List<Question> {
        return ArrayList<Question>()
    }

    override fun addUser(user: User): Boolean {
        return try {
            quizletApp.emailPassword.registerUserAsync(user.email, user.password) {
                if (!it.isSuccess) {
                    Log.e("TAG", "Error: ${it.error}")
                } else {
                    Log.i("TAG", "Successfully registered user.")
                }
            }
            true;
        } catch (exe: AppException) {
            false;
        }
    }

    // returns user with NULL, NULL, NULL if login fails
    override fun loginUser(email: String, password: String) : User {
        val creds = Credentials.emailPassword(email, password)
        return try {
            quizletApp.loginAsync(creds) {
                if (!it.isSuccess) {
                    Log.e("TAG", "Error: ${it.error}")
                } else {
                    Log.i("TAG", "Successfully logged in user.")
                }
            }
            User(email, password, null)
        } catch (app: AppException) {
            User(null, null, null);
        }
    }

    override fun onDestroy() {
        userRealm?.close()
    }

    override fun onStop() {
        userRealm?.close()
    }

}