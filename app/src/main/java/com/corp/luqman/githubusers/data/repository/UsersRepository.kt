package com.corp.luqman.githubusers.data.repository

import com.corp.luqman.githubusers.data.database.UserDao
import com.corp.luqman.githubusers.data.remote.ApiService
import javax.inject.Inject

class UsersRepository @Inject constructor(private val apiService: ApiService, private val dao: UserDao): UserRepositoryImpl{

}