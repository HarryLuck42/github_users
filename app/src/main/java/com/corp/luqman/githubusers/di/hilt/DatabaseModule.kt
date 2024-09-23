package com.corp.luqman.githubusers.di.hilt

import android.content.Context
import androidx.room.Room
import com.corp.luqman.githubusers.data.database.UserDao
import com.corp.luqman.githubusers.data.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideWeatherDao(movieDatabase: UserDatabase): UserDao
            = movieDatabase.userDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): UserDatabase
            = Room.databaseBuilder(
        context,
        UserDatabase::class.java,
        "user_database")
        .fallbackToDestructiveMigration()
        .build()
}