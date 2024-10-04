package com.abhi.workdoneapp.utils

import com.abhi.workdoneapp.data.Local.LocalTask
import com.abhi.workdoneapp.data.Task

// Task to Local
fun Task.toLocal() = LocalTask(
    id = id.toString(),
    title =  title,
    description = description,
    isCompleted = false,
)

// Local to External
fun LocalTask.toExternal() = Task(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted
)

@JvmName("localtoexternal")
fun List<LocalTask>.toExternal() = map(LocalTask::toExternal)