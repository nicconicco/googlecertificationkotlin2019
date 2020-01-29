package com.noorganization.googlecertificationkotlin.codelab_flow

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlowStepOne {
    /** https://codelabs.developers.google.com/codelabs/advanced-kotlin-coroutines/#7
     *
     * You can see how execution bounces between the collect lambda and the flow builder.
     * Every time the flow builder calls emit, it suspends until the element is completely processed.
     * Then, when another value is requested from the flow, it resumes from where it left off until it
     * calls emit again. When the flow builder completes, the Flow is cancelled and collect resumes,
     * letting and the calling coroutine prints "flow is completed."

      The call to collect is very important. Flow uses suspending operators like collect instead of
      exposing an Iterator interface so that it always knows when it's being actively consumed.
      More importantly, it knows when the caller can't request any more values so it can cleanup resources.
     */
    @Test
    fun watchFlowTest() {
        CoroutineScope(Dispatchers.IO).launch {
            makeFlow().collect { value ->
                Log.d("watchFlowTest", "got $value")
            }
            Log.d("watchFlowTest", "flow is completed")
        }
    }

    /**
     * Response:
     * D/watchFlowTest: sending first value
       D/watchFlowTest: got 1
       first value collected, sending another value
       got 2
       second value collected, sending a third value
       got 3
       done
       flow is completed
     */
}