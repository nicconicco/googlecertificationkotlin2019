package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.subcomponent.login.module

import dagger.Module

@Module(
    includes = [
        LoginViewModelModule::class,
        LoginRepositoryModule::class,
        LoginDataSourceModule::class
    ]
)
abstract class LoginModule