package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.subcomponent.login.module

import androidx.lifecycle.ViewModel
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.presentation.LoginViewModel
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
internal abstract class LoginViewModelModule {

    @Binds
    internal abstract fun bindLoginViewModelModule(viewModel: LoginViewModel): ViewModel
}
