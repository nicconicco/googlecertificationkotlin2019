package com.noorganization.googlecertificationkotlin.codelab_flow

import android.util.Log
import kotlinx.coroutines.flow.flow

fun makeFlow() = flow {
    emit(1)
    Log.d("watchFlowTest", "sending first value - makeFlow")

    emit(2)
    Log.d("watchFlowTest", "second value collected, sending another value - makeFlow")

    emit(3)
    Log.d("watchFlowTest", "three value collected, sending a third value - makeFlow")

    Log.d("watchFlowTest", "done")
}