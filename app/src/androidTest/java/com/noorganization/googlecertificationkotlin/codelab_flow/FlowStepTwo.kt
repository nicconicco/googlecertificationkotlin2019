package com.noorganization.googlecertificationkotlin.codelab_flow

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.*
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.System.currentTimeMillis

@RunWith(AndroidJUnit4::class)
class FlowStepTwo {

    /**
     * https://kotlinlang.org/docs/reference/coroutines/cancellation-and-timeouts.html#cancellation-is-cooperative
     */
    @Test
    fun testCancellationIsCooperative() = runBlocking {
        val startTime = currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) { // computation loop, just wastes CPU
                // print a message twice a second
                if (currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 2000L
                }
            }
        }
        delay(2300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }
}