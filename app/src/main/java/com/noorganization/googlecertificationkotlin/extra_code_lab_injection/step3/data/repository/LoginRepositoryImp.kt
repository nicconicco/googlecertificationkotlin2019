package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.repository

import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.datasource.RemoteLoginDataSource
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource
) : LoginRepository {
    override fun doLogin(): Boolean = remoteLoginDataSource.doLoginRemote()
}