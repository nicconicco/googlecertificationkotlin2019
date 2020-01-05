package com.noorganization.googlecertificationkotlin.dagger.fake_domain

import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.datasource.RemoteLoginDataSource
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryFakeImp @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource
) : LoginRepository {
    override fun doLogin(): Boolean {
        return false
    }
}