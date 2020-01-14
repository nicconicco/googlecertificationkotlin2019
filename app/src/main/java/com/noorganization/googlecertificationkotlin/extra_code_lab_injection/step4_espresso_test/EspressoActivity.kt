package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step4_espresso_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.noorganization.googlecertificationkotlin.R

class EspressoActivity : AppCompatActivity() {
    // ver onde esta classe eh chamada para verificar o comportamento
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_espresso)
    }
}
