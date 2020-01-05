package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.repository.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {
    private val _loginState = MutableLiveData<LoginViewState>()
    val loginState: LiveData<LoginViewState>
        get() = _loginState

    fun doLogin() {
        if(repository.doLogin()) {
            _loginState.value = LoginSuccess
        } else {
            _loginState.value = LoginError
        }
    }
}

sealed class LoginViewState
object LoginSuccess : LoginViewState()
object LoginError : LoginViewState()
