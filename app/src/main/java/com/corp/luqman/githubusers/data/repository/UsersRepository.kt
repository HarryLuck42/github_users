package com.corp.luqman.githubusers.data.repository

import com.corp.luqman.githubusers.data.database.UserDao
import com.corp.luqman.githubusers.data.models.response.User
import com.corp.luqman.githubusers.data.models.response.UserDetail
import com.corp.luqman.githubusers.data.models.response.UserLocal
import com.corp.luqman.githubusers.data.remote.ApiService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class UsersRepository @Inject constructor(private val apiService: ApiService, private val dao: UserDao): UserRepositoryImpl{

    override suspend fun getUsers(): MutableList<UserLocal>? = dao.getUsers()

    override fun getUsers(since: Int): Deferred<List<User>> = apiService.getUsers(since = since)

    override suspend fun searchUsers(title: String): MutableList<UserLocal>? = dao.searchUsers(key = title)

    override suspend fun getUsersById(id: Int): MutableList<UserLocal>? = dao.getUsersById(id)

    override suspend fun insertUser(user: UserLocal) = dao.insertUser(user)

    override suspend fun deleteFavorite(user: UserLocal) = dao.deleteUser(user)

    override fun getUserDetail(username: String): Deferred<UserDetail> = apiService.getUserDetail(username)

}