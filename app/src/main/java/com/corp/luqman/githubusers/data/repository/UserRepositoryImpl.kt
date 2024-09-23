package com.corp.luqman.githubusers.data.repository

import com.corp.luqman.githubusers.data.models.response.User
import com.corp.luqman.githubusers.data.models.response.UserDetail
import com.corp.luqman.githubusers.data.models.response.UserLocal
import kotlinx.coroutines.Deferred

interface UserRepositoryImpl {

    suspend fun getUsers(): MutableList<UserLocal>?

    suspend fun searchUsers(title: String): MutableList<UserLocal>?

    suspend fun getUsersById(id: Int): MutableList<UserLocal>?

    suspend fun insertUser(user: UserLocal)

    suspend fun deleteFavorite(user: UserLocal)

    fun getUsers(
        since : Int): Deferred<List<User>>

    fun getUserDetail(
        id : Int): Deferred<UserDetail>
}