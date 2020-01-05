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

package com.noorganization.googlecertificationkotlin.dagger.di

import com.example.android.dagger.di.RepositoryFakeModule
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.AppComponent
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.AppSubcomponents
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.subcomponent.login.module.LoginDataSourceModule
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.subcomponent.login.module.LoginViewModelModule
import dagger.Component
import javax.inject.Singleton

// Replacement for AppComponent in android tests
@Singleton
// Includes RepositoryFakeModule that overrides objects provided in StorageModule
@Component(modules = [
    RepositoryFakeModule::class,
    AppSubcomponents::class,
    LoginViewModelModule::class,
    LoginDataSourceModule::class])
interface TestAppComponent : AppComponent
