/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.noorganization.googlecertificationkotlin.codelab_mockito.tasks

import android.app.Application
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFilterType
import com.noorganization.googlecertificationkotlin.R
import com.noorganization.googlecertificationkotlin.codelab_mockito.Event
import com.noorganization.googlecertificationkotlin.codelab_mockito.data.Result
import com.noorganization.googlecertificationkotlin.codelab_mockito.data.source.DefaultTasksRepository
import kotlinx.coroutines.launch


/**
 * ViewModel for the task list screen.
 */
class TasksViewModel(application: Application) : AndroidViewModel(application) {

    // Note, for testing and architecture purposes, it's bad practice to construct the repository
    // here. We'll show you how to fix this during the codelab
    private val tasksRepository = DefaultTasksRepository.getRepository(application)

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _items: LiveData<List<Task>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            _dataLoading.value = true
            viewModelScope.launch {
                tasksRepository.refreshTasks()
                _dataLoading.value = false
            }
        }
        tasksRepository.observeTasks().switchMap { filterTasks(it) }

    }

    val items: LiveData<List<Task>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _currentFilteringLabel = MutableLiveData<Int>()
    val currentFilteringLabel: LiveData<Int> = _currentFilteringLabel

    private val _noTasksLabel = MutableLiveData<Int>()
    val noTasksLabel: LiveData<Int> = _noTasksLabel

    private val _noTaskIconRes = MutableLiveData<Int>()
    val noTaskIconRes: LiveData<Int> = _noTaskIconRes

    private val _tasksAddViewVisible = MutableLiveData<Boolean>()
    val tasksAddViewVisible: LiveData<Boolean> = _tasksAddViewVisible

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    private var currentFiltering = TasksFilterType.ALL_TASKS

    // Not used at the moment
    private val isDataLoadingError = MutableLiveData<Boolean>()

    private val _openTaskEvent = MutableLiveData<Event<String>>()
    val openTaskEvent: LiveData<Event<String>> = _openTaskEvent

    private val _newTaskEvent = MutableLiveData<Event<Unit>>()
    val newTaskEvent: LiveData<Event<Unit>> = _newTaskEvent

    private var resultMessageShown: Boolean = false

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    init {
        // Set initial state
        setFiltering(TasksFilterType.ALL_TASKS)
        loadTasks(true)
    }

    /**
     * Sets the current task filtering type.
     *
     * @param requestType Can be [TasksFilterType.ALL_TASKS],
     * [TasksFilterType.COMPLETED_TASKS], or
     * [TasksFilterType.ACTIVE_TASKS]
     */
    fun setFiltering(requestType: TasksFilterType) {
        currentFiltering = requestType

        // Depending on the filter type, set the filtering label, icon drawables, etc.
        when (requestType) {
            TasksFilterType.ALL_TASKS -> {
                setFilter(
                    R.string.label_all, R.string.no_tasks_all,
                    R.drawable.logo_no_fill, true
                )
            }
            TasksFilterType.ACTIVE_TASKS -> {
                setFilter(
                    R.string.label_active, R.string.no_tasks_active,
                    R.drawable.ic_check_circle_96dp, false
                )
            }
            TasksFilterType.COMPLETED_TASKS -> {
                setFilter(
                    R.string.label_completed, R.string.no_tasks_completed,
                    R.drawable.ic_verified_user_96dp, false
                )
            }
        }
        // Refresh list
        loadTasks(false)
    }

    private fun setFilter(
        @StringRes filteringLabelString: Int, @StringRes noTasksLabelString: Int,
        @DrawableRes noTaskIconDrawable: Int, tasksAddVisible: Boolean
    ) {
        _currentFilteringLabel.value = filteringLabelString
        _noTasksLabel.value = noTasksLabelString
        _noTaskIconRes.value = noTaskIconDrawable
        _tasksAddViewVisible.value = tasksAddVisible
    }

    fun clearCompletedTasks() {
        viewModelScope.launch {
            tasksRepository.clearCompletedTasks()
            showSnackbarMessage(R.string.completed_tasks_cleared)
        }
    }

    fun completeTask(task: Task, completed: Boolean) = viewModelScope.launch {
        if (completed) {
            tasksRepository.completeTask(task)
            showSnackbarMessage(R.string.task_marked_complete)
        } else {
            tasksRepository.activateTask(task)
            showSnackbarMessage(R.string.task_marked_active)
        }
    }

    /**
     * Called by the Data Binding library and the FAB's click listener.
     */
    fun addNewTask() {
        _newTaskEvent.value = Event(Unit)
    }

    /**
     * Called by Data Binding.
     */
    fun openTask(taskId: String) {
        _openTaskEvent.value = Event(taskId)
    }

    fun showEditResultMessage(result: Int) {
        if (resultMessageShown) return
        when (result) {
            EDIT_RESULT_OK -> showSnackbarMessage(R.string.successfully_saved_task_message)
            ADD_EDIT_RESULT_OK -> showSnackbarMessage(R.string.successfully_added_task_message)
            DELETE_RESULT_OK -> showSnackbarMessage(R.string.successfully_deleted_task_message)
        }
        resultMessageShown = true
    }

    private fun showSnackbarMessage(message: Int) {
        _snackbarText.value = Event(message)
    }

    private fun filterTasks(tasksResult: Result<List<Task>>): LiveData<List<Task>> {
        // TODO: This is a good case for liveData builder. Replace when stable.
        val result = MutableLiveData<List<Task>>()

        if (tasksResult is Result.Success) {
            isDataLoadingError.value = false
            viewModelScope.launch {
                result.value = filterItems(tasksResult.data, currentFiltering)
            }
        } else {
            result.value = emptyList()
            showSnackbarMessage(R.string.loading_tasks_error)
            isDataLoadingError.value = true
        }

        return result
    }

    /**
     * @param forceUpdate   Pass in true to refresh the data in the [TasksDataSource]
     */
    fun loadTasks(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    private fun filterItems(tasks: List<Task>, filteringType: TasksFilterType): List<Task> {
            val tasksToShow = ArrayList<Task>()
            // We filter the tasks based on the requestType
            for (task in tasks) {
                when (filteringType) {
                    TasksFilterType.ALL_TASKS -> tasksToShow.add(task)
                    TasksFilterType.ACTIVE_TASKS -> if (task.isActive) {
                        tasksToShow.add(task)
                    }
                    TasksFilterType.COMPLETED_TASKS -> if (task.isCompleted) {
                        tasksToShow.add(task)
                    }
                }
            }
            return tasksToShow
        }

    fun refresh() {
        _forceUpdate.value = true
    }
}
