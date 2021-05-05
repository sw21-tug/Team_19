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
import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import io.realm.mongodb.sync.SyncConfiguration
import org.bson.Document
import org.bson.types.ObjectId
import java.util.logging.Logger

class DBManager(private val quizletApp: App) : DBInterface {
    companion object {
        val LOG: Logger = Logger.getLogger(DBManager::class.java.name)
    }


    override fun addQuestion(question: Question) {
        val user = quizletApp.currentUser()
        checkNotNull(user)
        val config = SyncConfiguration.Builder(user, ObjectId(user.id))
                .allowWritesOnUiThread(true)
                .build()

        val userRealm = Realm.getInstance(config)
        userRealm.executeTransactionAsync() { transactionRealm ->
            transactionRealm.insert(question)
            LOG.severe("did something")
        }
    }

    override fun getAllQuestions(): ImmutableList<Question> {
        val user = quizletApp.currentUser()
        checkNotNull(user)
        val config = SyncConfiguration.Builder(user, ObjectId(user.id))
                .allowWritesOnUiThread(true)
                .allowQueriesOnUiThread(true)
                .build()

        var results: RealmResults<Question>? = null
        val userRealm = Realm.getInstance(config)
        userRealm.executeTransaction { transactionRealm ->
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

    @Throws(AppException::class)
    override fun addUser(email: String, password: String): Boolean {
        var wasSuccessful = false
        quizletApp.emailPassword.registerUserAsync(email, password) {
            if (!it.isSuccess) {
                LOG.fine("Error: ${it.error}")
            } else {
                LOG.fine("Successfully registered user.")
                wasSuccessful = true
            }
        }
        return wasSuccessful
    }

    @Throws(AppException::class)
    override fun loginUser(email: String, password: String): Boolean {
        var wasSuccessful = false
        val creds = Credentials.emailPassword(email, password)
        quizletApp.loginAsync(creds) {
            if (!it.isSuccess) {
                LOG.fine("Error: ${it.error}")
            } else {
                LOG.fine("Successfully logged in user.")
                wasSuccessful = true
            }
        }
        return wasSuccessful
    }

    override fun getHighscoreOfCurrentUser(): Int {
        TODO("Not yet implemented")
    }

    override fun updateUserHighscore(newHighscore: Int) {
        TODO("Not yet implemented")
    }

}