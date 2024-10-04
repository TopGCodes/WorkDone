package com.abhi.workdoneapp.data

import com.abhi.workdoneapp.base.WorkType
import com.abhi.workdoneapp.data.Local.TasksDao
import com.abhi.workdoneapp.di.ApplicationScope
import com.abhi.workdoneapp.di.DefaultDispatcher
import com.abhi.workdoneapp.utils.toExternal
import com.abhi.workdoneapp.utils.toLocal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class DefaultTaskRepository @Inject constructor(
    private val tasksDao: TasksDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope
) : TaskRepository {
    override suspend fun createTask(title : String, description : String, workType : WorkType): String {
        //generating id would be a complex operation, so we are doing it on different thread
        val taskID = withContext(dispatcher){
            UUID.randomUUID().toString()
        }
        val task = Task(
            id = taskID,
            title = title,
            description = description,
        )
        tasksDao.upsertTask(task.toLocal())
        return taskID
    }

    override fun getAllTasksStream(): Flow<List<Task>> {
       return  tasksDao.getAllTasks().map { tasks ->
            withContext(dispatcher){
                tasks.toExternal()
            }
       }
    }

    override suspend fun deleteTask(id: String) {
        tasksDao.deleteTask(id)
    }

    override suspend fun updatetask(id: String, title : String, description : String) {
        val task = getTaskById(id)?.copy(
            title = title,
            description = description
        ) ?: throw IllegalArgumentException("Error with ID")
        tasksDao.upsertTask(task.toLocal())
    }

    override suspend fun getTaskById(id: String): Task? {
        val externaltask = tasksDao.getTaskbyId(id).toExternal()
        return externaltask
    }
}