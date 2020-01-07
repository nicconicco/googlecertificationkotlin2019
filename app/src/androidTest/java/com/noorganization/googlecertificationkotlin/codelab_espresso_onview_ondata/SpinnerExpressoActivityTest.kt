package com.noorganization.googlecertificationkotlin.codelab_espresso_onview_ondata

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.noorganization.googlecertificationkotlin.R
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpinnerExpressoActivityTest {

    @get:Rule
    var mainActivityActivityTestRule = ActivityTestRule(SpinnerEspressoActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals(
            "com.noorganization.googlecertificationkotlin",
            appContext.packageName
        )
    }

    /**
     * Iterate through the spinner, selecting each item and
     * checking to see if it matches the string in the array.
     */
    @Test
    fun iterateSpinnerItems() {
        val myArray = mainActivityActivityTestRule.activity.resources
            .getStringArray(R.array.labels_array)

        // Iterate through the spinner array of items.
        val size = myArray.size
        for (i in 0 until size) {
            // Find the spinner and click on it.
            onView(withId(R.id.label_spinner)).perform(click())
            // Find the spinner item and click on it.
            onData(`is`(myArray[i])).perform(click())
            // Find the text view and check that the spinner item
            // is part of the string.
            onView(withId(R.id.text_phonelabel))
                .check(matches(withText(containsString(myArray[i]))))
        }
    }
}