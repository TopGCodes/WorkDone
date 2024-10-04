package com.abhi.workdoneapp.data

import androidx.room.Entity
import com.abhi.workdoneapp.base.WorkType


data class Task(
    val id : String,
    val title : String,
    val description  : String,
    val isCompleted : Boolean = false,
    val taskType : WorkType = WorkType.ALL
)