package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.noorganization.googlecertificationkotlin.GoogleCertificationKotlinApplication
import com.noorganization.googlecertificationkotlin.R
import com.noorganization.googlecertificationkotlin.databinding.ActivityLoginDaggerBinding
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step1.CarInjectNativeActivity
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step4_espresso_test.EspressoActivity
import kotlinx.android.synthetic.main.activity_login_dagger.*
import javax.inject.Inject

class LoginDaggerActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginDaggerBinding

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_dagger)

        (application as GoogleCertificationKotlinApplication).appComponent.loginComponent().create()
            .inject(this)

        binding.viewmodel = loginViewModel

        loginViewModel.loginState.observe(this, Observer<LoginViewState> { state ->
            when (state) {
                is LoginViewState.LoginSuccess -> {
                    startActivity(Intent(this, CarInjectNativeActivity::class.java))
                }
                is LoginViewState.LoginError -> {
                    startActivity(Intent(this, EspressoActivity::class.java))
                }
            }
        })
    }
}
