package com.noorganization.googlecertificationkotlin.extra_code_lab_injection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.noorganization.googlecertificationkotlin.R

class CarInjectNativeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_inject_native)

        // Sem injecao nativa
        val car = Car()
        car.start()

        // Com Injecao nativa
        val engine = Engine()
        val carInjected = CarInjectedNative(engine)
        car.start()
    }
}
