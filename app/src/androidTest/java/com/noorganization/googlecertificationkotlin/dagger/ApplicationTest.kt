/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.dagger

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.noorganization.googlecertificationkotlin.R
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.presentation.LoginDaggerActivity
import org.junit.Test

class ApplicationTest {

    @Test
    fun runApp() {
        ActivityScenario.launch(LoginDaggerActivity::class.java)

        onView(withId(R.id.username)).perform(typeText("username"), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(typeText("password"), closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())

        // Registration/T&Cs
        onView(ViewMatchers.withText("Fake implements works!")).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )

        Thread.sleep(5000)
    }
}
