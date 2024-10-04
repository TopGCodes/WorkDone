package com.abhi.workdoneapp.data

import com.abhi.workdoneapp.base.WorkType
import com.abhi.workdoneapp.data.Local.LocalTask
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasksStream(): Flow<List<Task>>

    suspend fun createTask(title : String, description : String, workType: WorkType): String

    suspend fun deleteTask(id : String)

    suspend fun updatetask(id : String, task : String, description: String)

    suspend fun getTaskById(id : String) : Task?


}