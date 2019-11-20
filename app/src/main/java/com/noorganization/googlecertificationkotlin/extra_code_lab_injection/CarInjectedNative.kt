package com.noorganization.googlecertificationkotlin.extra_code_lab_injection

class CarInjectedNative(private val engine: Engine) {
    fun start() {
        engine.start()
    }
}