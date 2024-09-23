package com.corp.luqman.githubusers.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.corp.luqman.githubusers.data.models.response.UserLocal

@Dao
interface UserDao {
    @Query("SELECT * from users")
    suspend fun getUsers(): MutableList<UserLocal>?

    @Query("SELECT * from users where username like :key")
    suspend fun searchUsers(key: String): MutableList<UserLocal>?

    @Query("SELECT * from users where id = :id")
    suspend fun getUsersById(id: Int): MutableList<UserLocal>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserLocal)

    @Delete
    suspend fun deleteUser(user: UserLocal)
}