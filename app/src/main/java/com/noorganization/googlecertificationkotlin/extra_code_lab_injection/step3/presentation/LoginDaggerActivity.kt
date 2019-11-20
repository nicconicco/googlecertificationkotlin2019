package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.noorganization.googlecertificationkotlin.GoogleCertificationKotlinApplication
import com.noorganization.googlecertificationkotlin.R
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step1.CarInjectNativeActivity
import kotlinx.android.synthetic.main.activity_dagger.*
import javax.inject.Inject

class LoginDaggerActivity : AppCompatActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

        (application as GoogleCertificationKotlinApplication).appComponent.loginComponent().create()
            .inject(this)

        loginViewModel.loginState.observe(this, Observer<LoginViewState> { state ->
            when (state) {
                is LoginSuccess -> {
                    startActivity(Intent(this, CarInjectNativeActivity::class.java))
                    finish()
                }
                is LoginError -> Toast.makeText(this, "Erro", Toast.LENGTH_LONG).show()
            }
        })

        text2.setOnClickListener {
            loginViewModel.doLogin()
        }
    }
}
