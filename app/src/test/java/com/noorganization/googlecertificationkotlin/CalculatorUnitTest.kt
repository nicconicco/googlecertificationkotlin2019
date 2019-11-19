package com.noorganization.googlecertificationkotlin

import com.noorganization.googlecertificationkotlin.code_lab_unit_test.Calculator
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.closeTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CalculatorUnitTest {
    private var mCalculator: Calculator? = null

    /**
     * Set up the environment for testing
     */
    @Before
    fun setUp() {
        mCalculator = Calculator()
    }

    /**
     * Test for simple addition
     */
    @Test
    fun addTwoNumbers() {
        val resultAdd = mCalculator!!.add(1.0, 1.0)
        assertThat(resultAdd, `is`(equalTo(2.0)))
    }

    @Test
    fun addTwoNumbersNegative() {
        val resultAdd = mCalculator!!.add(-1.0, 2.0)
        assertThat(resultAdd, `is`(equalTo(1.0)))
    }

    @Test
    fun addTwoNumbersFloats() {
        val resultAdd = mCalculator!!.add(1.111, 1.111)
        assertThat(resultAdd, `is`(closeTo(2.222, 0.01)))
    }

    @Test
    fun subTwoNumbers() {
        val resultSub = mCalculator!!.sub(1.0, 1.0)
        assertThat(resultSub, `is`(equalTo(0.0)))
    }

    @Test
    fun subWorksWithNegativeResult() {
        val resultSub = mCalculator!!.sub(1.0, 17.0)
        assertThat(resultSub, `is`(equalTo(-16.0)))
    }

    @Test
    fun mulTwoNumbers() {
        val resultMul = mCalculator!!.mul(32.0, 2.0)
        assertThat(resultMul, `is`(equalTo(64.0)))
    }

    @Test
    fun divTwoNumbers() {
        val resultDiv = mCalculator!!.div(32.0, 2.0)
        assertThat(resultDiv, `is`(equalTo(16.0)))
    }

    @Test
    fun divTwoNumbersZero() {
        val resultDiv = mCalculator!!.div(32.0, 0.0)
        assertThat(resultDiv, `is`(equalTo(java.lang.Double.POSITIVE_INFINITY)))
    }
}