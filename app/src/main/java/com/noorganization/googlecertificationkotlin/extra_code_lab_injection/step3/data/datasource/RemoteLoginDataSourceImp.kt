package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.datasource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteLoginDataSourceImp @Inject constructor() : RemoteLoginDataSource {
    override fun doLoginRemote(): Boolean {
        return true
    }
}