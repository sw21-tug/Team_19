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
class AccountFragmentTest {

    @get: Rule
    val activityRule: ActivityScenarioRule<SplashActivity> =
        ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun setUp() {
        launchFragmentInContainer<AccountFragment>(
            fragmentArgs = null, // Bundle
            initialState = Lifecycle.State.RESUMED, // Lifecycle.State
            factory = null // FragmentFactory
        )
    }

    @Test
    fun test_isScoreDisplayed() {
        onView(ViewMatchers.withId(R.id.text_view_account_score))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.textScoreHeadline))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.textEmail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.text_view_account_email))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.textEmail2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.text_view_total_questions))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}