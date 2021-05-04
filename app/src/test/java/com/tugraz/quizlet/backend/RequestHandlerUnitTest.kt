package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.DBInterface
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import io.realm.RealmList
import org.bson.types.ObjectId
import org.junit.After
import org.junit.Assert.*
import org.junit.Test

import org.junit.Before
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
        verify(mockDBInterface, times(1)).addUser(eq(email), eq(password))
    }

    @Test
    fun testGetUser() {
        val email: String = "test@mock.junit"
        val password: String = "123456"
        `when`(mockDBInterface.loginUser(email, password)).thenReturn(true)
        val actualUser = requestHandler.loginUser(email, password)
        verify(mockDBInterface, times(1)).loginUser(email, password)
    }

    @Test
    fun testAddQuestion() {
        val category = Question_category("animals", "desc")
        val question: String = "what is the fastest mammal?"
        val answer: String = "cheetah"
        val wrongAnswersRealmList: RealmList<String> = RealmList("sloth","antelope","rabbit")
        val wrongAnswersImmutableList: ImmutableList<String> = ImmutableList.of("sloth","antelope","rabbit")
        val expectedQuestion = Question(ObjectId(), category, question, answer, null, wrongAnswersRealmList)
        requestHandler.addQuestion(category, question, answer, wrongAnswersImmutableList)
        verify(mockDBInterface, times(1)).addQuestion(expectedQuestion)
    }

    @Test
    fun testGetAllQuestions() {
        val expectedQuestions:ImmutableList<Question> = generateRandomQuestion(5)
        `when`(mockDBInterface.getAllQuestions()).thenReturn(expectedQuestions)
        val actualQuestions = requestHandler.getAllQuestion()
        verify(mockDBInterface, times(1)).getAllQuestions()
        assertEquals(expectedQuestions, actualQuestions)
    }

    @Test
    fun testGetAllQuestionsOfCategoryWithFoundCategory() {
        val category = "hi"
        val expectedQuestions:ImmutableList<Question> = generateRandomQuestionForCategory(4, category)
        `when`(mockDBInterface.getAllQuestionsForCategory(category)).thenReturn(expectedQuestions)
        val actualQuestions = requestHandler.getAllQuestionForCategory(category)
        verify(mockDBInterface, times(1)).getAllQuestionsForCategory(category)
        assertEquals(expectedQuestions, actualQuestions)
    }

    @Test
    fun testGetAllQuestionsOfCategoryWithNoFoundCategory() {
        val category = "category1"
        `when`(mockDBInterface.getAllQuestionsForCategory(category)).thenReturn(ImmutableList.of())
        val actualQuestions = requestHandler.getAllQuestionForCategory(category)
        verify(mockDBInterface, times(1)).getAllQuestionsForCategory(category)
        assertTrue(actualQuestions.isEmpty())
    }
    @Test
    private fun testStartNewGame() {
        val expectedQuestions:ImmutableList<Question> = generateRandomQuestion(5)
        val lastQuestion = expectedQuestions.last()

        `when`(mockDBInterface.getAllQuestions()).thenReturn(expectedQuestions)
        val question = requestHandler.startNewGameAndReturnTheFirstQuestion()
        verify(mockDBInterface, times(1)).getAllQuestions()
        assertEquals(lastQuestion, question)
    }

    @Test
    private fun testStartNewGameNoQuestions() {
        val expectedQuestions:ImmutableList<Question> = ImmutableList.of()

        `when`(mockDBInterface.getAllQuestions()).thenReturn(expectedQuestions)
        val question = requestHandler.startNewGameAndReturnTheFirstQuestion()
        verify(mockDBInterface, times(1)).getAllQuestions()
        assertNull(question)
    }

    @Test
    private fun testNextQuestion() {
        val expectedQuestions:ImmutableList<Question> = generateRandomQuestion(5)
        requestHandler.setRemainingQuestionForCurrentGame(expectedQuestions)
        val lastQuestion = expectedQuestions.last()

        val question = requestHandler.getNextQuestionAndUpdateRemainingAndUpdateHighscore()

        assertEquals(lastQuestion, question)
        assertEquals(5, requestHandler.getHighscoreCurrentGame())
    }

    @Test
    private fun testEndGameWithNewHighscore() {
        val expectedHighscore = 3
        requestHandler.setHighscoreCurrentGame(expectedHighscore)
        val question = requestHandler.getNextQuestionAndUpdateRemainingAndUpdateHighscore()

        `when`(mockDBInterface.getHighscoreForCurrentUser()).thenReturn(0)

        val currHighscore = requestHandler.endCurrentGameAndReturnCurrentHighscoreAndUpdateDatabase()
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

    private fun generateRandomQuestionForCategory(numberOfRandomQuestions: Int, category: String): ImmutableList<Question> {
        val questionCategory = Question_category(category, "")
        val immutableListBuilder: ImmutableList.Builder<Question> = ImmutableList.Builder()
        for (i in 1..numberOfRandomQuestions) {
            val question = Question(ObjectId(), questionCategory, "b$i", "c$i", null, RealmList("d$i", "e$i", "f$i"))
            immutableListBuilder.add(question)
        }
        return immutableListBuilder.build()
    }


}