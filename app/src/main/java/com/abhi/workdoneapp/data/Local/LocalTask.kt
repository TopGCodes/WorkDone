package com.abhi.workdoneapp.data.Local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abhi.workdoneapp.base.WorkType

@Entity(tableName = "Tasks")
data class LocalTask(
    @PrimaryKey
    val id : String,
    val title : String = "",
    val description : String ="",
    val isCompleted : Boolean = false,
    val workType : WorkType = WorkType.ALL
)
