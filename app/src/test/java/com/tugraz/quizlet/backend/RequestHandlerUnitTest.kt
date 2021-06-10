package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.DBInterface
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import io.realm.RealmList
import org.bson.types.ObjectId
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class RequestHandlerUnitTest {

    private lateinit var requestHandler: RequestHandler
    private lateinit var mockDBInterface: DBInterface

    @Before
    fun setUp() {
        mockDBInterface = mock(DBInterface::class.java)
        requestHandler = RequestHandler(mockDBInterface)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testAddUser() {
        val email: String = "test@mock.junit"
        val password: String = "123456"
        requestHandler.addUser(email, password)
        verify(mockDBInterface, times(1)).addUser(email, password)
    }

    @Test
    fun testLoginUser() {
        val email: String = "test@mock.junit"
        val password: String = "123456"
        `when`(mockDBInterface.loginUser(email, password)).thenReturn(true)
        requestHandler.loginUser(email, password)
        verify(mockDBInterface, times(1)).loginUser(email, password)
    }

    @Test
    fun testAddQuestion() {
        val category = Question_category("animals", "desc")
        val question: String = "what is the fastest mammal?"
        val answer: String = "cheetah"
        val wrongAnswersRealmList: RealmList<String> = RealmList("sloth", "antelope", "rabbit")
        val wrongAnswersImmutableList: ImmutableList<String> =
            ImmutableList.of("sloth", "antelope", "rabbit")
        val expectedQuestion =
            Question(ObjectId(), category, question, answer, null, wrongAnswersRealmList)
        requestHandler.addQuestion(category, question, answer, wrongAnswersImmutableList)
        verify(mockDBInterface, times(1)).addQuestion(expectedQuestion)
    }

    @Test
    fun testNextQuestion() {
        val expectedQuestions: ImmutableList<Question> = generateRandomQuestion(10)
        requestHandler.setRemainingQuestionForCurrentGame(ArrayList(expectedQuestions))
        val firstQuestion = expectedQuestions.first()

        val question = requestHandler.getNextQuestionAndUpdateRemainingAndUpdateHighscore()

        assertEquals(firstQuestion, question)
        assertEquals(
            0,
            requestHandler.getHighscoreCurrentGame()
        ) // 0 because of first question of the game
    }

    @Test
    fun testEndGameWithNewHighscore() {
        val expectedHighscore = 3
        requestHandler.setHighscoreCurrentGame(expectedHighscore)

        `when`(mockDBInterface.getHighscoreOfCurrentUser()).thenReturn(0)

        val currHighscore =
            requestHandler.endCurrentGameAndReturnCurrentHighscoreAndUpdateDatabase()
        assertEquals(expectedHighscore, currHighscore)
        verify(mockDBInterface, times(1)).updateUserHighscore(currHighscore)
    }

    private fun generateRandomQuestion(numberOfRandomQuestions: Int): ImmutableList<Question> {
        val immutableListBuilder: ImmutableList.Builder<Question> = ImmutableList.Builder()
        for (i in 1..numberOfRandomQuestions) {
            val question = generateRandomQuestionForCategory(1, "a$i")[0]
            immutableListBuilder.add(question)
        }
        return immutableListBuilder.build()
    }

    private fun generateRandomQuestionForCategory(
        numberOfRandomQuestions: Int,
        category: String
    ): ImmutableList<Question> {
        val questionCategory = Question_category(category, "")
        val immutableListBuilder: ImmutableList.Builder<Question> = ImmutableList.Builder()
        for (i in 1..numberOfRandomQuestions) {
            val question = Question(
                ObjectId(),
                questionCategory,
                "b$i",
                "c$i",
                null,
                RealmList("d$i", "e$i", "f$i")
            )
            immutableListBuilder.add(question)
        }
        return immutableListBuilder.build()
    }
}