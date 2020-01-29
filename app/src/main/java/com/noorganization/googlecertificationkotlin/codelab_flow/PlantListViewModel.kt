/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.noorganization.googlecertificationkotlin.codelab_flow

import androidx.lifecycle.*
import com.noorganization.googlecertificationkotlin.codelab_flow.model.GrowZone
import com.noorganization.googlecertificationkotlin.codelab_flow.model.NoGrowZone
import com.noorganization.googlecertificationkotlin.codelab_flow.model.Plant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * The [ViewModel] for fetching a list of [Plant]s.
 */
/**
 * Para lembrar da importancia entre livedata e flow
 *
 * Using Flow, it's natural to collect data in the ViewModel, Repository, or other data layers when needed.

   Since Flow is not tied to the UI, you don't need a UI observer to collect a flow.
   This is a big difference from LiveData which always requires a UI-observer to run.
   It is not a good idea to try to observe a LiveData in your ViewModel because it doesn't have an appropriate observation lifecycle.
 *
 */
@ExperimentalCoroutinesApi
@FlowPreview
class PlantListViewModel internal constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {
    private val growZoneChannel = ConflatedBroadcastChannel<GrowZone>()

    /**
     * Request a snackbar to display a string.
     *
     * This variable is private because we don't want to expose [MutableLiveData].
     *
     * MutableLiveData allows anyone to set a value, and [PlantListViewModel] is the only
     * class that should be setting values.
     */
    private val _snackbar = MutableLiveData<String?>()

    /**
     * Request a snackbar to display a string.
     */
    val snackbar: LiveData<String?>
        get() = _snackbar

    private val _spinner = MutableLiveData<Boolean>(false)
    /**
     * Show a loading spinner if true
     */
    val spinner: LiveData<Boolean>
        get() = _spinner

    /**
     * The current growZone selection.
     */
    private val growZone = MutableLiveData<GrowZone>(NoGrowZone)

    /**
     * A list of plants that updates based on the current filter.
     */

    init {
        clearGrowZoneNumber()  // keep this

        // fetch the full plant list
        growZoneChannel.asFlow()
            .mapLatest { growZone ->
                _spinner.value = true
                if (growZone == NoGrowZone) {
                    plantRepository.tryUpdateRecentPlantsCache()
                } else {
                    plantRepository.tryUpdateRecentPlantsForGrowZoneCache(growZone)
                }
            }
            .onCompletion {  _spinner.value = false }
            .catch { throwable ->  _snackbar.value = throwable.message  }
            .launchIn(viewModelScope)
    }


    val plantsUsingFlow: LiveData<List<Plant>> = growZoneChannel.asFlow()
        .flatMapLatest { growZone ->
            if (growZone == NoGrowZone) {
                plantRepository.plantsFlow
            } else {
                plantRepository.getPlantsWithGrowZoneFlow(growZone)
            }
        }.asLiveData()

    /**
     * Filter the list to this grow zone.
     *
     * In the starter code version, this will also start a network request. After refactoring,
     * updating the grow zone will automatically kickoff a network request.
     */
    fun setGrowZoneNumber(num: Int) {
        growZone.value = GrowZone(num)
        growZoneChannel.offer(GrowZone(num))

//        launchDataLoad {
//            plantRepository.tryUpdateRecentPlantsForGrowZoneCache(GrowZone(num))
//        }
    }

    fun clearGrowZoneNumber() {
        growZone.value = NoGrowZone
        growZoneChannel.offer(NoGrowZone)

//        launchDataLoad {
//            plantRepository.tryUpdateRecentPlantsCache()
//        }
    }

    /**
     * Return true iff the current list is filtered.
     */
    fun isFiltered() = growZone.value != NoGrowZone

    /**
     * Called immediately after the UI shows the snackbar.
     */
    fun onSnackbarShown() {
        _snackbar.value = null
    }

    /**
     * Helper function to call a data load function with a loading spinner; errors will trigger a
     * snackbar.
     *
     * By marking [block] as [suspend] this creates a suspend lambda which can call suspend
     * functions.
     *
     * @param block lambda to actually load data. It is called in the viewModelScope. Before calling
     *              the lambda, the loading spinner will display. After completion or error, the
     *              loading spinner will stop.
     */
    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _spinner.value = true
                block()
            } catch (error: Throwable) {
                _snackbar.value = error.message
            } finally {
                _spinner.value = false
            }
        }
    }
}
