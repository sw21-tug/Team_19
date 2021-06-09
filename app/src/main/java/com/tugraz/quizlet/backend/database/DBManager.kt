package com.tugraz.quizlet.backend.database

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import com.tugraz.quizlet.backend.database.model.User
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.kotlin.where
import io.realm.mongodb.App
import io.realm.mongodb.AppException
import io.realm.mongodb.Credentials
import io.realm.mongodb.ErrorCode.Type.UNKNOWN
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

    override fun getQuestionCount(): String {
        val config = SyncConfiguration.Builder(quizletApp.currentUser(), ANON_ID)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
        val realm = Realm.getInstance(config) ?: return ""
        return realm.where(Question::class.java).count().toString() ?: return ""
    }

    override fun getQuestionCategoryCount(): String {
        val config = SyncConfiguration.Builder(quizletApp.currentUser(), ANON_ID)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
        val realm = Realm.getInstance(config) ?: return ""
        val categories = realm.where(Question_category::class.java).findAll()
        return categories.stream().distinct().count().toString()
    }

    override fun getAllQuestionsForCategory(categoryName: String): ImmutableList<Question> {
        throw NotImplementedError()
    }

    @Throws(Exception::class)
    override fun addUser(email: String, password: String): Boolean {
        var addUser = false
        val thread = Thread(Runnable {
            try {
                quizletApp.emailPassword.registerUser(email, password)
                addUser = true
            } catch (e: Exception) {
                addUser = false
                e.printStackTrace()
            }
        })

        thread.start()
        thread.join()
        if(addUser == false)
            throw Exception()

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
    @Throws(Exception::class)
    override fun loginUser(email: String, password: String): Boolean {
        var loginSuccessful = false
        val thread = Thread(Runnable {
            try {
                val creds = Credentials.emailPassword(email, password)
                quizletApp.login(creds)
                loginSuccessful = true
            }
            catch (exe: Exception) {
                LOG.severe("something")
            }
        })

        thread.start()
        thread.join()

        if (loginSuccessful == false)
            throw Exception()

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

    override fun getEmailOfCurrentUser(): String {
        val user = quizletApp.currentUser() ?: return ""
        return user.profile.email.toString()
    }

}