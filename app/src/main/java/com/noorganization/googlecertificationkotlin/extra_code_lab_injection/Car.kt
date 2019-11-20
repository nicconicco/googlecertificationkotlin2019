package com.noorganization.googlecertificationkotlin.extra_code_lab_injection

class Car {
    private val engine = Engine()

    fun start() {
        engine.start()
    }
}