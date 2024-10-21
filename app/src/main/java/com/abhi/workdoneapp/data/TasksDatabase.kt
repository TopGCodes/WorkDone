package com.abhi.workdoneapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhi.workdoneapp.data.Local.LocalTask
import com.abhi.workdoneapp.data.Local.TasksDao

@Database(entities = [LocalTask::class], version = 1 , exportSchema =false)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun getTasksDao() : TasksDao
}