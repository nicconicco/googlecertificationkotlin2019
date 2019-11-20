package com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step2


class Car(private val model: ModelCar) {
    fun showModelCar() = model.getModel()
}