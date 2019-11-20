package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.noorganization.googlecertificationkotlin.R

class LoginDaggerActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)
    }
}
