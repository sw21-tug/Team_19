package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList
import com.tugraz.quizlet.backend.database.DBInterface
import com.tugraz.quizlet.backend.database.model.Question
import com.tugraz.quizlet.backend.database.model.Question_category
import com.tugraz.quizlet.backend.database.model.User
import io.realm.RealmList
import org.bson.types.ObjectId
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
        `when`(mockDBInterface.loginUser(email, password)).thenReturn(expectedUser)
        val actualUser = requestHandler.loginUser(email, password)
        verify(mockDBInterface, times(1)).loginUser(email, password)
        assertEquals(expectedUser, actualUser)
    }

    @Test
    fun testAddQuestion() {
        val category = Question_category("animals", "desc")
        val question: String = "what is the fastest mammal?"
        val answer: String = "cheetah"
        val wrongAnswersRealmList: RealmList<String> = RealmList("sloth","antelope","rabbit")
        val wrongAnswersImmutableList: ImmutableList<String> = ImmutableList.of("sloth","antelope","rabbit")
        val expectedQuestion = Question(ObjectId(), category, question, answer, wrongAnswersRealmList)
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
            val question = Question(ObjectId(), questionCategory, "b$i", "c$i", RealmList("d$i", "e$i", "f$i"))
            immutableListBuilder.add(question)
        }
        return immutableListBuilder.build()
    }
}