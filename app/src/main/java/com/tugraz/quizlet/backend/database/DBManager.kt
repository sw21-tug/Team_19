package com.tugraz.quizlet.backend.database

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import com.tugraz.quizlet.backend.database.model.User
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.mongodb.App
import io.realm.mongodb.AppException
import io.realm.mongodb.Credentials
import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import io.realm.mongodb.mongo.iterable.FindIterable
import io.realm.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.awaitAll
import org.bson.Document
import org.bson.types.ObjectId
import java.util.logging.Logger
import kotlin.jvm.Throws

class DBManager(private val quizletApp: App) : DBInterface {
    companion object {
        val LOG: Logger = Logger.getLogger(DBManager::class.java.name)
    }

    private var user: io.realm.mongodb.User? = null
    private var realm: Realm? = null

    init {
        loginUser("domitestere", "lalalal123e")

        Thread.sleep(1000)
        //updateUserHighscore(5)
        getHighscoreForCurrentUser()
    }

    override fun addQuestion(question: Question) {
        user = quizletApp.currentUser()
        question.userCreated = user!!.id;
        val config = SyncConfiguration.Builder(user!!, user!!.id)
            .allowWritesOnUiThread(true)
            .build()

        realm = Realm.getInstance(config)
        realm?.executeTransactionAsync() { transactionRealm ->
            transactionRealm.insert(question)
            LOG.severe("did something")
        }
    }

    override fun getAllQuestions(): ImmutableList<Question> {
        user = quizletApp.currentUser()
        val config = SyncConfiguration.Builder(user!!, user!!.id)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        var results: RealmResults<Question>? = null
        realm = Realm.getInstance(config)
        realm?.executeTransaction { transactionRealm ->
            results =
                transactionRealm.where(Question::class.java)?.findAll() as RealmResults<Question>;
        }

        return ImmutableList.copyOf(results?.subList(0, results!!.size))
    }


    override fun getAllQuestionsForCategory(categoryName: String): ImmutableList<Question> {
        return ImmutableList.of()
    }

    override fun addUser(email: String, password: String): Boolean {
        try {
            quizletApp.emailPassword.registerUserAsync(email, password) {
                if (!it.isSuccess) {
                    LOG.fine("Error: ${it.error}")
                } else {
                    LOG.fine("Successfully registered user.")
                }
            }
        } catch (exe: AppException) {
            false;
        }
        Thread.sleep(1000)
        loginUser(email, password)
        Thread.sleep(1000)
        // insert into custom user data
        val anonymousCredentials: Credentials = Credentials.anonymous()
        user = quizletApp.currentUser()
        val userid = quizletApp.currentUser()?.id
        quizletApp.loginAsync(anonymousCredentials) {
            if (it.isSuccess) {
                val mongoClient: MongoClient =
                    user?.getMongoClient("mongodb-atlas")!! // service for MongoDB Atlas cluster containing custom user data
                val mongoDatabase: MongoDatabase =
                    mongoClient.getDatabase("Quizlet")!!
                val mongoCollection: MongoCollection<Document> =
                    mongoDatabase.getCollection("Users")!!
                mongoCollection.insertOne(Document("_id", userid).append("highscore", 0).append("userCreated", userid))
                    .getAsync { result ->
                        if (result.isSuccess) {
                            LOG.fine("jsda")
                        }
                    }
            }
        }
        return true;
    }

    // returns user with NULL, NULL, NULL if login fails
    @Throws(AppException::class)
    override fun loginUser(email: String, password: String): Boolean {
        val creds = Credentials.emailPassword(email, password)
        quizletApp.loginAsync(creds) {
            if (!it.isSuccess) {
                LOG.fine("Error: ${it.error}")
            } else {
                LOG.fine("Successfully logged in user.")
            }
        }
        user = quizletApp.currentUser()
        return true
    }

    override fun getHighscoreForCurrentUser(): Int? {
        var customUserScore: Document = Document()
        customUserScore = quizletApp.currentUser()?.customData!!
        /*
        val anonymousCredentials: Credentials = Credentials.anonymous()
        quizletApp.loginAsync(anonymousCredentials) {
            if (it.isSuccess) {

            }
        }*/
        Thread.sleep(1000)

        return 0
    }

    override fun updateUserHighscore(newHighscore: Int) {
        val anonymousCredentials: Credentials = Credentials.anonymous()
        val user = quizletApp.currentUser()
        quizletApp.loginAsync(anonymousCredentials) {
            if (it.isSuccess) {
                val mongoClient: MongoClient =
                    user?.getMongoClient("mongodb-atlas")!! // service for MongoDB Atlas cluster containing custom user data
                val mongoDatabase: MongoDatabase =
                    mongoClient.getDatabase("Quizlet")!!
                val mongoCollection: MongoCollection<Document> =
                    mongoDatabase.getCollection("Users")!!
                mongoCollection.updateOne(Document("_id", user.id), Document("highscore", newHighscore))
                    .getAsync { result ->
                        if (result.isSuccess) {
                            LOG.fine("jsda")
                        }
                    }
            }
        }
    }

    override fun onDestroy() {
        realm?.close()
    }

    override fun onStop() {
        realm?.close()
    }

}