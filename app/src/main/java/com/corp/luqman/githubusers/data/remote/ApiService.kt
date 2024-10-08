package com.corp.luqman.githubusers.data.remote

import com.corp.luqman.githubusers.data.models.response.User
import com.corp.luqman.githubusers.data.models.response.UserDetail
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(
        @Query("per_page") page: Int = 30,
        @Query("since") since: Int,
    ): Deferred<List<User>>

    @GET("user/{id}")
    fun getUserDetail(
        @Path("id") id : Int
    ): Deferred<UserDetail>
}