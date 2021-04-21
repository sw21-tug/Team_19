package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
        val user: User = User(email, password)
        requestHandler.addUser(email, password)
        verify(mockDBInterface, times(1)).addUser(user)
    }

    @Test
    fun testGetUser() {
        val email: String = "test@mock.junit"
        val password: String = "123456"
        val expectedUser: User = User(email, password)
        `when`(mockDBInterface.getUser(email)).thenReturn(expectedUser)
        val actualUser = requestHandler.getUser(email)
        verify(mockDBInterface, times(1)).getUser(email)
        assertEquals(expectedUser, actualUser)
    }

    @Test
    fun testAddQuestion() {
        val category: String = "animals"
        val question: String = "what is the fastest mammal?"
        val answer: String = "cheetah"
        val wrongAnswers: ImmutableList<String> = ImmutableList.of("sloth","antelope","rabbit")
        val expectedQuestion = Question(category, question, answer, wrongAnswers)
        requestHandler.addQuestion(category, question, answer, wrongAnswers)
        verify(mockDBInterface, times(1)).addQuestion(expectedQuestion)
    }

    @Test
    fun testGetAllQuestions() {
        val expectedQuestions:ImmutableList<Question> = generateRandomQuestion(5)
        `when`(mockDBInterface.getAllQuestions()).thenReturn(expectedQuestions)
        val actualQuestions = requestHandler.getAllQuestion()
        verify(mockDBInterface, times(1)).getAllQuestions()
        assertEquals(expectedQuestions, actualQuestions)
        WebServer(requestHandler).startServer()
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

    private fun generateRandomQuestion(numberOfRandomQuestions: Int): ImmutableList<Question> {
        val immutableListBuilder: ImmutableList.Builder<Question> = ImmutableList.Builder()
        for (i in 1..numberOfRandomQuestions) {
            val question = generateRandomQuestionForCategory(1, "a$i")[0]
            immutableListBuilder.add(question)
        }
        return immutableListBuilder.build()
    }

    private fun generateRandomQuestionForCategory(numberOfRandomQuestions: Int, category: String): ImmutableList<Question> {
        val immutableListBuilder: ImmutableList.Builder<Question> = ImmutableList.Builder()
        for (i in 1..numberOfRandomQuestions) {
            val question = Question(category, "b$i", "c$i", ImmutableList.of("d$i", "e$i", "f$i"))
            immutableListBuilder.add(question)
        }
        return immutableListBuilder.build()
    }
}