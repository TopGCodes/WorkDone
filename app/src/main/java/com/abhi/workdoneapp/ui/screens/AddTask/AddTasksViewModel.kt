package com.abhi.workdoneapp.ui.screens.AddTask

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi.workdoneapp.base.Route
import com.abhi.workdoneapp.data.TaskRepository
import com.abhi.workdoneapp.utils.EXISTED_TASKID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class AddTaskUIState(
    val title: String = "",
    val description: String = "",
    val userMessage: String? = null, // it is for showing task related info to user
    val isCompleted: Boolean = false,// to know whether to completed or not
)

@HiltViewModel
class AddTasksViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val handle: SavedStateHandle
) : ViewModel() {

    val existedTaskID = handle.getStateFlow<String>(EXISTED_TASKID_KEY, "")

    private val _AddTaskUIState: MutableStateFlow<AddTaskUIState> =
        MutableStateFlow(AddTaskUIState())
    val AddTaskUIState = _AddTaskUIState.asStateFlow()

    private val FabButtonState: MutableStateFlow<String> = MutableStateFlow("Save")
    val buttonState = FabButtonState.asStateFlow()

    fun loadTask(id: String) = viewModelScope.launch {
        val existedTask = repository.getTaskById(id = id)
        if (existedTask == null) {
            _AddTaskUIState.update {
                it.copy(
                    userMessage = "Task Doesn't Exist"
                )
            }
        } else {
            Log.d("asd", "Task Exists - $existedTask")
            handle[EXISTED_TASKID_KEY] = id
            Log.d("asd", "Task Exists id is  - ${existedTaskID.value}")
            Log.d("asd", "Task Exists id is from direct handle  - ${handle.get<String>(EXISTED_TASKID_KEY)}")
            _AddTaskUIState.update {
                it.copy(
                    title = existedTask.title,
                    description = existedTask.description,
                    isCompleted = existedTask.isCompleted
                )
            }
        }
    }

    fun changeFabState() {
        FabButtonState.update { "Update" }
    }

    fun stopShowingSnackbar(){
        _AddTaskUIState.update {
            it.copy(userMessage = null)
        }
    }

    fun saveTask() = viewModelScope.launch {
        if (existedTaskID.value.isBlank()) {
            _AddTaskUIState.update {
                it.copy(userMessage = "Your Task is Saved")
            }
            repository.createTask(_AddTaskUIState.value.title, _AddTaskUIState.value.description)
        } else {
            UpdateTask()
        }
    }

    fun UpdateTask() = viewModelScope.launch {
        // gettting existingtask from the room to update it
        val existingTask = existedTaskID?.let { repository.getTaskById(it.value) }
        if (existingTask == null) {
            _AddTaskUIState.update {
                it.copy(userMessage = "Task Doesn't Exist")
            }
        } else {
            if (existedTaskID.value != null) {
                repository.updatetask(
                    id = existedTaskID.value,
                    _AddTaskUIState.value.title,
                    _AddTaskUIState.value.description
                )
            }
        }
    }

    fun updateTitle(title: String) {
        _AddTaskUIState.update {
            it.copy(title = title)
        }
    }

    fun updateDescription(desc: String) {
        _AddTaskUIState.update {
            it.copy(description = desc)
        }
    }
}