package com.tugraz.quizlet.frontend

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.tugraz.quizlet.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AddQuestionFragmentTest {
    @get: Rule
    val activityRule: ActivityScenarioRule<StartActivity> =
        ActivityScenarioRule(StartActivity::class.java)


    @Before
    fun setUp() {
        launchFragmentInContainer<AddQuestionFragment>(
            fragmentArgs = null, // Bundle
            initialState = Lifecycle.State.RESUMED, // Lifecycle.State
            factory = null // FragmentFactory
        )
        val scenario = launchFragmentInContainer<AddQuestionFragment>()
    }

    @Test
    fun test_isSubmitButtonDisplayed() {
        onView(ViewMatchers.withId(R.id.submitQuestion))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_isSpinnerDisplayed() {
        onView(ViewMatchers.withId(R.id.spinner))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_isQuestionInputDisplayed() {
        onView(ViewMatchers.withId(R.id.editTextQuestion))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_isAnswerRightInputDisplayed() {
        onView(ViewMatchers.withId(R.id.editTextRightAnswer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_areWrongAnswerInputsDisplayed() {
        onView(ViewMatchers.withId(R.id.editTextWrong1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.editTextWrong2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.editTextWrong3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}