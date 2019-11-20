package com.noorganization.googlecertificationkotlin.extra_code_lab_injection

class Car {

    init {
        val car = Car()
        car.start()
    }

    private val engine = Engine()

    fun start() {
        engine.start()
    }
}