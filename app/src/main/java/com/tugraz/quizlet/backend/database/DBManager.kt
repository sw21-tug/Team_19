package com.tugraz.quizlet.backend.database

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import com.tugraz.quizlet.backend.database.model.User
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.mongodb.App
import io.realm.mongodb.AppException
import io.realm.mongodb.Credentials
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.types.ObjectId
import java.util.logging.Logger

class DBManager(private val quizletApp: App) : DBInterface {
    companion object {
        val LOG: Logger = Logger.getLogger(DBManager::class.java.name)
    }
    private var user: io.realm.mongodb.User? = null
    private var userRealm: Realm? = null

    init {
        // testFunctionality - how to use the db manager
        loginUser("testuser", "testpassword")
        val rightAnswer = "1923"
        val list: RealmList<String> = RealmList()
        list.add("1921")
        list.add("1950")
        list.add("1930")
        val category = Question_category( "für die gerti" ,"Gertis Geburtstag")
        val question = Question(
            ObjectId(), category,"Wann ist Gerti geboren?", rightAnswer, list)
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
            LOG.severe( "did something")
        }
    }

    override fun getAllQuestions() : ImmutableList<Question> {
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

        return ImmutableList.copyOf(results?.subList(0, results!!.size))
    }


    override fun getAllQuestionsForCategory(categoryName: String) : ImmutableList<Question> {
        return ImmutableList.of()
    }

    override fun addUser(user: User): Boolean {
        return try {
            quizletApp.emailPassword.registerUserAsync(user.email, user.password) {
                if (!it.isSuccess) {
                    LOG.fine("Error: ${it.error}")
                } else {
                    LOG.fine( "Successfully registered user.")
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
                    LOG.fine( "Error: ${it.error}")
                } else {
                    LOG.fine( "Successfully logged in user.")
                }
            }
            User(email, password)
        } catch (app: AppException) {
            User("Invalid", "Invalid");
        }
    }

    override fun onDestroy() {
        userRealm?.close()
    }

    override fun onStop() {
        userRealm?.close()
    }

}