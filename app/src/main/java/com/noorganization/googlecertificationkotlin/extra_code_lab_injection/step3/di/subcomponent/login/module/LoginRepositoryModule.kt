package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.subcomponent.login.module

import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.repository.LoginRepository
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.repository.LoginRepositoryImp
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
internal abstract class LoginRepositoryModule {

    @Binds
    internal abstract fun bindLoginRepositoryModule(repository: LoginRepositoryImp): LoginRepository
}