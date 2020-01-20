package com.noorganization.googlecertificationkotlin.inject.step2

import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step2.Car
import com.noorganization.googlecertificationkotlin.extra_code_lab_injection.step2.Engine
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

    @Test
    fun Strem_inteiros() {
        val list = mutableListOf(1, 2, 3, 4, 3, 5, 6, 5, 6, 8)
        val k = 3
        printDups(list, k)
    }

    fun printDups(list: MutableList<Int>, k: Int) {

        val listHelper: MutableList<Int> = mutableListOf()
        val interator = list.iterator()
        while (interator.hasNext()) {
            val value = interator.next()
            updateContador(k, listHelper)

            if (checkDups(listHelper, value)) {
                print("VALOR = $value\n")
            }
            listHelper.add(value)
        }
    }

    private fun updateContador(
        k: Int,
        listHelper: MutableList<Int>
    ) {
        if (listHelper.size == k) {
            listHelper.removeAt(0)
        }
    }

    private fun checkDups(
        listHelper: MutableList<Int>,
        value: Int
    ) = listHelper.contains(value)
}

class Ferrari : Engine {
    override fun getModel(): String {
        return "Meu modelo é uma Ferrari"
    }
}

class Mercedes : Engine {
    override fun getModel(): String {
        return "Meu modelo é uma Mercedes"
    }
}
