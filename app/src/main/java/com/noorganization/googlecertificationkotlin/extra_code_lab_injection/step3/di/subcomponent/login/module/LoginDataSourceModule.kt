package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.di.subcomponent.login.module

import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.datasource.RemoteLoginDataSource
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.datasource.RemoteLoginDataSourceImp
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
internal abstract class LoginDataSourceModule {

    @Binds
    internal abstract fun bindLoyaltyDataSourceModule(remote: RemoteLoginDataSourceImp): RemoteLoginDataSource
}
