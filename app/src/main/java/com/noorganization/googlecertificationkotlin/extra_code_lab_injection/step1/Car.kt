package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step1

class Car {
    private val engine =
        Engine()

    fun start() {
        engine.start()
    }
}