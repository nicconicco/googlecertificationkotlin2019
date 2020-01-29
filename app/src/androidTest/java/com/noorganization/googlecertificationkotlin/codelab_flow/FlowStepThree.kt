package com.noorganization.googlecertificationkotlin.codelab_flow

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlowStepThree {
    /** https://codelabs.developers.google.com/codelabs/advanced-kotlin-coroutines/#7
     *
     * The flow lambda starts from the top each time collect is called. This is important if the flow
     * performed expensive work like making a network request. Also, since we applied the take(2)
     * operator, the flow will only produce two values. It will not resume the flow lambda again
     * after the second call to emit, so the line "second value collected..." will never print.
     *
     * By default, a Flow will restart from the top every time a terminal operator is applied.
     * This is important if the Flow performs expensive work, such as making a network request.
     */
    @Test
    fun testTakeInFlow() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            val repeatableFlow = makeFlow().take(2)  // we only care about the first two elements
            Log.d("watchFlowTest", "first collection")
            repeatableFlow.collect()
            Log.d("watchFlowTest", "collecting again")
            repeatableFlow.collect()
            Log.d("watchFlowTest", "second collection completed")
        }
    }
}