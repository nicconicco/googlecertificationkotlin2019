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

package com.example.android.dagger.di

import com.noorganization.googlecertificationkotlin.dagger.fake_domain.LoginRepositoryFakeImp
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.repository.LoginRepository
import dagger.Binds
import dagger.Module

// Overrides StorageModule in android tests
@Module
abstract class RepositoryFakeModule {

    // Makes Dagger provide FakeStorage when a Storage type is requested
    @Binds
    abstract fun provideRepositoryFake(repositoryFake: LoginRepositoryFakeImp): LoginRepository
}
