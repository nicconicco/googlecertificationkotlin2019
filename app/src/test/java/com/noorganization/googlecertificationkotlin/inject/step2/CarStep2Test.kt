package com.noorganization.googlecertificationkotlin.inject.step2

import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step2.Car
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step2.ModelCar
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CarStep2Test {

    private lateinit var car: Car

    @Test
    fun Car_ShowFerrariModel() {
        car = Car(Ferrari())
        assertEquals(car.showModelCar(), "Meu modelo é uma Ferrari")
    }

    @Test
    fun Car_ShowMercedes() {
        car = Car(Mercedes())
        assertEquals(car.showModelCar(), "Meu modelo é uma Mercedes")
    }
}

class Ferrari : ModelCar {
    override fun getModel(): String {
        return "Meu modelo é uma Ferrari"
    }
}

class Mercedes : ModelCar {
    override fun getModel(): String {
        return "Meu modelo é uma Mercedes"
    }
}
