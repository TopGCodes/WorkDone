package com.abhi.workdoneapp.ui.screens.AddTask

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.abhi.workdoneapp.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTasksViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val handle : SavedStateHandle
) : ViewModel(){


}