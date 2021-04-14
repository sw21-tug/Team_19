package com.tugraz.quizlet.backend

import org.junit.After
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

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
        Mockito.verify(mockDBInterface, times(1)).addUser(ArgumentMatchers.eq(user))
    }


}