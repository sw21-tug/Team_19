package com.tugraz.quizlet.frontend

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.tugraz.quizlet.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class LanguageTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<StartActivity> =
        ActivityScenarioRule(StartActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.start)).check(matches(isDisplayed()))
    }

    @Test
    fun test_areFragmentButtonDisplayed() {

        onView(withId(R.id.button_start))
            .check(matches(isDisplayed()))
        onView(withId(R.id.button_exit))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isLanguageSwitched() {
        onView(withId(R.id.settings)).perform(click())
        onView(withId(R.id.button_start)).check(matches(withText("播放")))
    }
}