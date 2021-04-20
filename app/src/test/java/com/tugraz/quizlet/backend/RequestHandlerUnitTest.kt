package com.tugraz.quizlet.backend

import com.google.common.collect.ImmutableList
import org.junit.After
import org.junit.Assert.assertEquals
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
        `when`(mockDBInterface.getUser(email, password)).thenReturn(expectedUser)
        val actualUser = requestHandler.getUser(email, password)
        verify(mockDBInterface, times(1)).getUser(email, password)
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

}