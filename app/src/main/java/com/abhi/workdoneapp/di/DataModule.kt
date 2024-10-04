package com.abhi.workdoneapp.di

import android.content.Context
import androidx.room.Room
import com.abhi.workdoneapp.data.DefaultTaskRepository
import com.abhi.workdoneapp.data.Local.TasksDao
import com.abhi.workdoneapp.data.TaskRepository
import com.abhi.workdoneapp.data.TasksDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Singleton
    @Binds
    abstract fun bindsRepository(repository: DefaultTaskRepository) : TaskRepository
}


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): TasksDatabase {
        val db =
            Room.databaseBuilder(context.applicationContext, TasksDatabase::class.java, "TasksDB")
                .build()
        return db
    }

    @Provides
    fun providesTasksDao(database: TasksDatabase) : TasksDao = database.getTasksDao();
}