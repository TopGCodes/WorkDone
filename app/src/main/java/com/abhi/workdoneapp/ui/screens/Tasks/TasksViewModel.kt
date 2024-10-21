package com.abhi.workdoneapp.ui.screens.Tasks

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.workdoneapp.base.UIState
import com.abhi.workdoneapp.base.WorkType
import com.abhi.workdoneapp.data.Task
import com.abhi.workdoneapp.data.TaskRepository
import com.abhi.workdoneapp.utils.SAVED_FILTER_TYPE
import com.abhi.workdoneapp.utils.WhileUISubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async
import javax.inject.Inject


data class TasksUIState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val isLongClicked  : Boolean = false
)

data class Filter(
    val type: String = "ALL",
    val noTasksLabel: String = "No Tasks"
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val repository: TaskRepository,
    val handle: SavedStateHandle
) : ViewModel() {

    private val savedFilterType = handle.getStateFlow(SAVED_FILTER_TYPE, WorkType.ALL)

    private val filterInfo: Flow<Filter> = savedFilterType.map { getFilterInfo(it) }

    private val isLongClickedToDelete : MutableStateFlow<Boolean> = MutableStateFlow(false)

    val isLoading = MutableStateFlow(false)

    private val filteredTasks = combine(
        repository.getAllTasksStream(), savedFilterType
    ) { tasks, filterType ->
        filterTasks(filterType, tasks)
    }.map { UIState.Success(it) }
        .catch<UIState<List<Task>>> { emit(UIState.Error("Something Went Wrong")) }


     val uiState: StateFlow<TasksUIState> = combine(
        filterInfo, isLoading, filteredTasks, isLongClickedToDelete
    )
    { filter, isLoading, filterdTasks, isLongClicked ->

             when (filterdTasks) {
                 is UIState.Error -> TasksUIState(userMessage = filterdTasks.message)
                 is UIState.Loading -> TasksUIState(isLoading = isLoading)
                 is UIState.Success -> TasksUIState(tasks = filterdTasks.list, isLoading = false, isLongClicked = isLongClicked)
             }

    }.stateIn(
        scope = viewModelScope,
        started = WhileUISubscribed,
        initialValue = TasksUIState(isLoading = isLoading.value)
    )

    fun setFilter(filter: String) {
        handle[SAVED_FILTER_TYPE] = filter
    }

    fun startShowingLoading() {
        isLoading.value = true
    }

    fun showDeleteButton(){
        isLongClickedToDelete.value = true
        Log.d("asd", "longCliked - ${isLongClickedToDelete.value}")
    }

    private fun filterTasks(filterType: WorkType, tasks: List<Task>): List<Task> {
        val taskstoShow = ArrayList<Task>()
        for (task in tasks) {
            when (filterType) {
                WorkType.ALL -> {
                    taskstoShow.add(task)
                }

                WorkType.WORK -> {
                    taskstoShow.add(task)
                }

                WorkType.PERSONAL -> {
                    taskstoShow.add(task)
                }

                WorkType.COMPLETED -> {
                    taskstoShow.add(task)
                }

                WorkType.ACTIVE -> {
                    taskstoShow.add(task)
                }
            }
        }
        return taskstoShow

    }


    private fun getFilterInfo(workType: WorkType): Filter {
        return when (workType) {
            WorkType.ALL -> {
                Filter(
                    type = "ALL",
                    noTasksLabel = "You have No Tasks"
                )
            }

            WorkType.WORK -> {
                Filter(
                    type = "WORK",
                    noTasksLabel = "You have No Tasks"
                )
            }

            WorkType.PERSONAL -> {
                Filter(
                    type = "PERSONAL",
                    noTasksLabel = "You have No Tasks"
                )
            }

            WorkType.COMPLETED -> {
                Filter(
                    type = "COMPLETED",
                    noTasksLabel = "You have No Tasks"
                )
            }

            WorkType.ACTIVE -> {
                Filter(
                    type = "COMPLETED",
                    noTasksLabel = "You have No Tasks"
                )
            }
        }
    }

     fun  deleteTaskById(id : String)  = viewModelScope.launch {
         Log.d("asd", "id _ $id")
        repository.deleteTask(id)
    }
}