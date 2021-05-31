package com.tugraz.quizlet.backend

import com.tugraz.quizlet.backend.database.DBManager
import io.realm.mongodb.App
import io.realm.mongodb.RealmResultTask
import io.realm.mongodb.User
import io.realm.mongodb.mongo.MongoClient
import io.realm.mongodb.mongo.MongoCollection
import io.realm.mongodb.mongo.MongoDatabase
import io.realm.mongodb.mongo.result.UpdateResult
import org.bson.Document
import org.junit.*
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

class DBManagerUnitTest {
    companion object {
        private const val USER_ID = "user id"
    }


    private lateinit var dbManager: DBManager
    private lateinit var mockedApp: App
    private lateinit var mockedUser: User
    private lateinit var mockedClient: MongoClient
    private lateinit var mockedMongoDatabase: MongoDatabase
    private lateinit var mockedMongoCollection: MongoCollection<Document>

    @Before
    fun setUp() {
        mockedApp = Mockito.mock(App::class.java)
        dbManager = DBManager(mockedApp)
        mockedUser = Mockito.mock(User::class.java)
        mockedClient = Mockito.mock(MongoClient::class.java)
        mockedMongoDatabase = Mockito.mock(MongoDatabase::class.java)
        mockedMongoCollection =
            Mockito.mock(MongoCollection::class.java) as MongoCollection<Document>

        Mockito.`when`(this.mockedApp.currentUser()).thenReturn(mockedUser)
        Mockito.`when`(this.mockedUser.getMongoClient(anyString())).thenReturn(mockedClient)
        Mockito.`when`(this.mockedUser.id).thenReturn(USER_ID)
        Mockito.`when`(this.mockedClient.getDatabase("Quizlet")).thenReturn(mockedMongoDatabase)
        Mockito.`when`(this.mockedMongoDatabase.getCollection("Users"))
            .thenReturn(mockedMongoCollection)
        Mockito.`when`(this.mockedMongoDatabase.getCollection("Questions"))
            .thenReturn(mockedMongoCollection)
    }

    @After
    fun tearDown() {
    }

    @Ignore("Not implemented yet")
    @Test
    fun testGetHighscoreOfCurrentUser() {
        val expectedHighscore = 420
        val expectedUserDocument = Document("_id", USER_ID)
        val mockedResultTask =
            Mockito.mock(RealmResultTask::class.java) as RealmResultTask<Document>
        val mockedDocument = Mockito.mock(Document::class.java)
        Mockito.`when`(this.mockedMongoCollection.findOne(expectedUserDocument))
            .thenReturn(mockedResultTask)
        Mockito.`when`(mockedResultTask.get()).thenReturn(mockedDocument)
        Mockito.`when`(mockedDocument["highscore"]).thenReturn(expectedHighscore)

        val actualHighscore = dbManager.getHighscoreOfCurrentUser()

        Mockito.verify(mockedMongoCollection, Mockito.times(1)).findOne(expectedUserDocument)
        Mockito.verify(mockedResultTask, Mockito.times(1)).get()
        Assert.assertEquals(expectedHighscore, actualHighscore)
    }

    @Ignore("Not implemented yet")
    @Test
    fun testUpdateUserHighscore() {
        val newHighscore = 420
        val expectedUserDocument = Document("_id", USER_ID)
        val expectedHighscoreDocument = Document("highscore", newHighscore)
        val mockedResultTask =
            Mockito.mock(RealmResultTask::class.java) as RealmResultTask<UpdateResult>
        val mockedUpdateResult = Mockito.mock(UpdateResult::class.java)
        Mockito.`when`(
            this.mockedMongoCollection.updateOne(
                expectedUserDocument,
                expectedHighscoreDocument
            )
        ).thenReturn(mockedResultTask)
        Mockito.`when`(mockedResultTask.get()).thenReturn(mockedUpdateResult)

        dbManager.updateUserHighscore(newHighscore)

        Mockito.verify(mockedMongoCollection, Mockito.times(1))
            .updateOne(expectedUserDocument, expectedHighscoreDocument)
        Mockito.verify(mockedResultTask, Mockito.times(1)).get()
    }
}