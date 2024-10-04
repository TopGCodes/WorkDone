package com.abhi.workdoneapp.data.Local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.abhi.workdoneapp.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM Tasks")
    fun getAllTasks(): Flow<List<LocalTask>>

    @Upsert
    suspend fun upsertTask(task : LocalTask)

    @Query("DELETE from Tasks where id= :taskid")
    suspend fun deleteTask(taskid : String)

    @Query("SELECT * FROM Tasks where id = :id")
    suspend fun getTaskbyId(id : String) : LocalTask

}