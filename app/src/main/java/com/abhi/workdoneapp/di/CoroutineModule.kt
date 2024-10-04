package com.abhi.workdoneapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope


@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule{

    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher()  = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher() = Dispatchers.IO

    @Provides
    @ApplicationScope
    @Singleton
    fun providesCoroutineScope(
       @IoDispatcher dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(dispatcher + SupervisorJob())
}
