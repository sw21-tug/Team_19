package com.tugraz.quizlet.backend.database

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.User
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.kotlin.where
import io.realm.mongodb.App
import io.realm.mongodb.AppException
import io.realm.mongodb.Credentials
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.types.ObjectId
import java.lang.RuntimeException
import java.util.*
import java.util.logging.Logger
import kotlin.jvm.Throws

class DBManager(private val quizletApp: App) : DBInterface {
    companion object {
        val LOG: Logger = Logger.getLogger(DBManager::class.java.name)
        const val ANON_ID = "60a3cab5f8fa77c5b6172d89"
    }

    private lateinit var anon: io.realm.mongodb.User

    init {
        /** This needs to be here for the public question **/
        val thread = Thread(Runnable {
            val creds = Credentials.anonymous()
            quizletApp.login(creds)
            val currentUser = quizletApp.currentUser()
            if(currentUser != null) {
                anon = currentUser
            } else {
                throw RuntimeException("Failed to load default/anonymous user!")
            }
        })

        thread.start()
        thread.join()
    }

    override fun addQuestion(question: Question) {
        question.userCreated = ANON_ID
        val config = SyncConfiguration.Builder(anon, ANON_ID)
            .allowWritesOnUiThread(true)
            .build()

        val realm = Realm.getInstance(config) ?: return
        realm.executeTransactionAsync { transactionRealm ->
            transactionRealm.insert(question)
        }
    }

    override fun getAllQuestionsAsync(callback: (ImmutableList<Question>) -> Unit) {
        val config = SyncConfiguration.Builder(anon, ANON_ID)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.getInstanceAsync(config, object : Realm.Callback() {
            override fun onSuccess(realm: Realm) {
                val result = realm.where(Question::class.java).findAllAsync() ?: return

                result.addChangeListener(RealmChangeListener{ fetchedResult ->
                    run {
                        if (fetchedResult == null)
                            callback(ImmutableList.of())
                        else
                            callback(ImmutableList.copyOf(fetchedResult))
                    }
                })
            }
        })
    }

    override fun getAllQuestionsForCategory(categoryName: String): ImmutableList<Question> {
        throw NotImplementedError()
    }

    override fun addUser(email: String, password: String): Boolean {
        val thread = Thread(Runnable {
            try {
                quizletApp.emailPassword.registerUser(email, password)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        thread.start()
        thread.join()
        loginUser(email, password)

        // insert into custom user data

        val user = quizletApp.currentUser() ?: return false

        val config = SyncConfiguration.Builder(user, user.id)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
        val newUser = User(ObjectId(user.id), 0, user.id)


        val realm = Realm.getInstance(config) ?: return false
        realm.executeTransaction { transactionRealm ->
            transactionRealm.insert(newUser)
            LOG.severe("did something")
        }
        return true
    }

    // returns user with NULL, NULL, NULL if login fails
    @Throws(AppException::class)
    override fun loginUser(email: String, password: String): Boolean {
        val thread = Thread(Runnable {
            val creds = Credentials.emailPassword(email, password)
            quizletApp.login(creds)
        })

        thread.start()
        thread.join()

        return true
    }

    override fun getHighscoreOfCurrentUser(): Int {
        var highscore: Long? = -1

        val user = quizletApp.currentUser() ?: return 0

        val config = SyncConfiguration.Builder(user, user.id)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        val realm = Realm.getInstance(config) ?: return 0
        realm.executeTransaction { transactionRealm ->
            val result = transactionRealm.where(User::class.java)?.findFirst()

            if(result != null) {
                highscore = result.highscore
            }
        }


        if (Objects.equals(highscore, -1L) || highscore == null) {
            return 0
        }

        return highscore!!.toInt()
    }

    override fun updateUserHighscore(newHighscore: Int) {
        val user = quizletApp.currentUser() ?: return

        val config = SyncConfiguration.Builder(user, user.id)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        val realm = Realm.getInstance(config) ?: return
        realm.executeTransaction { transactionRealm ->
            val thisuser: User =
                transactionRealm.where<User>().findFirst()!!
            thisuser.highscore = newHighscore.toLong()
        }
    }

}