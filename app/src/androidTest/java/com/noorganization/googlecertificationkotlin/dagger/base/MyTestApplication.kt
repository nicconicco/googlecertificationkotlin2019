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

package com.noorganization.googlecertificationkotlin.dagger.base

import com.noorganization.googlecertificationkotlin.GoogleCertificationKotlinApplication
import com.noorganization.googlecertificationkotlin.dagger.di.DaggerTestAppComponent
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.AppComponent

/**
 * MyTestApplication will override MyApplication in android tests
 */
class MyTestApplication : GoogleCertificationKotlinApplication() {

    override fun initializeComponent(): AppComponent {
        // Creates a new TestAppComponent that injects fakes types
        return DaggerTestAppComponent.create()
    }
}
