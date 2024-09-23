package com.corp.luqman.githubusers.di.hilt

import com.corp.luqman.githubusers.utils.rx.AppSchedulerProvider
import com.corp.luqman.githubusers.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppSchedulerProvider(): SchedulerProvider{
        return AppSchedulerProvider()
    }
}