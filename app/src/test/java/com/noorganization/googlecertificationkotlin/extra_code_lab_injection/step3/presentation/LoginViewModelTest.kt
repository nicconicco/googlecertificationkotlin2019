package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.data.repository.LoginRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    private val loginRepository = mockk<LoginRepository>()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun whenViewModel_call_doLogin_asnwer_trueResponse() {
        prepareScenario(true)
        viewModel.doLogin()
        assert(viewModel.loginState.value is LoginViewState.LoginSuccess)
    }

    @Test
    fun whenViewModel_call_doLogin_asnwer_falseResponse() {
        prepareScenario(false)
        viewModel.doLogin()
        assert(viewModel.loginState.value is LoginViewState.LoginError)
    }

    private fun prepareScenario(value: Boolean) {
        // Para ter como material de ajuda, quando algum método apenas você precisa passar.
//        every { loginRepository.someOtherMethodWithoutResponse() } just runs
        every { loginRepository.doLogin() } returns value
    }
}