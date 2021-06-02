package com.tugraz.quizlet.backend.database

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.User
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import io.realm.mongodb.App
import io.realm.mongodb.AppException
import io.realm.mongodb.Credentials
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.types.ObjectId
import java.util.logging.Logger
import kotlin.jvm.Throws

class DBManager(private val quizletApp: App) : DBInterface {
    companion object {
        val LOG: Logger = Logger.getLogger(DBManager::class.java.name)
    }

    private var anon: io.realm.mongodb.User? = null

    private var user: io.realm.mongodb.User? = null
    private var realm: Realm? = null

    init {
        /** This needs to be here for the public question **/
        val thread = Thread(Runnable {
            val creds = Credentials.anonymous()
            quizletApp.login(creds)
            anon = quizletApp.currentUser()
        })

        thread.start()
        thread.join()
    }

    override fun addQuestion(question: Question){
        question.userCreated = anon!!.id
        val config = SyncConfiguration.Builder(anon!!, anon!!.id)
            .allowWritesOnUiThread(true)
            .build()

        realm = Realm.getInstance(config)
        realm?.executeTransactionAsync() { transactionRealm ->
            transactionRealm.insert(question)
        }
    }

    override fun getAllQuestions(): ImmutableList<Question> {
        val config = SyncConfiguration.Builder(anon!!, anon!!.id)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        var results: RealmResults<Question>? = null
        realm = Realm.getInstance(config)
        realm?.executeTransaction { transactionRealm ->
            results =
                    transactionRealm.where(Question::class.java)?.findAll() as RealmResults<Question>;
        }

        if (results == null || results!!.isEmpty()) {
            return ImmutableList.of()
        }

        return ImmutableList.copyOf(results?.subList(0, results!!.size))
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
        user = quizletApp.currentUser()

        val config = SyncConfiguration.Builder(user!!, user!!.id)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()
        val user = User(ObjectId(user!!.id), 0, user!!.id)


        realm = Realm.getInstance(config)
        realm?.executeTransaction { transactionRealm ->
            transactionRealm.insert(user)
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

        user = quizletApp.currentUser()
        return true
    }

    override fun getHighscoreOfCurrentUser(): Int {
        var highscore : Long = -1

        val config = SyncConfiguration.Builder(user!!, user!!.id)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        realm = Realm.getInstance(config)
        realm?.executeTransaction { transactionRealm ->
            val result =
                transactionRealm.where(User::class.java)?.findFirst()
            highscore = result?.highscore!!
        }


        if (highscore.equals(-1)) {
            return 0
        }

        return highscore.toInt()
    }

    override fun updateUserHighscore(newHighscore: Int) {
        val config = SyncConfiguration.Builder(user!!, user!!.id)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        realm = Realm.getInstance(config)
        realm?.executeTransaction { transactionRealm ->
            val thisuser: User =
                transactionRealm.where<User>().findFirst()!!
            thisuser.highscore = newHighscore.toLong()
        }
    }

}