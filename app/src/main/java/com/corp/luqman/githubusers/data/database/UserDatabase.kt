package com.corp.luqman.githubusers.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.corp.luqman.githubusers.data.models.response.ConverterListInt
import com.corp.luqman.githubusers.data.models.response.UserLocal

@TypeConverters(ConverterListInt::class)
@Database(entities = [UserLocal::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase(){
    abstract fun userDao() : UserDao
}