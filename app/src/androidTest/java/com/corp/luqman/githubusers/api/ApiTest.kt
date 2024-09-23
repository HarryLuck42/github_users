package com.corp.luqman.githubusers.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.corp.luqman.githubusers.BuildConfig
import com.corp.luqman.githubusers.data.remote.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalCoroutinesApi::class)
class ApiTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var api: ApiService

    @Before
    fun init(){
        val client = OkHttpClient.Builder()
        client.apply {
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            callTimeout(60, TimeUnit.SECONDS)
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(logging)
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        api = retrofit.create(ApiService::class.java)
    }

    @Test
    fun testGetUsers() = runTest {
        val users = api.getUsers(since = 0).await()
        assertThat(users).isNotEmpty()
    }

    @Test
    fun testGetUserDetail() = runTest {
        val detail = api.getUserDetail(1).await()
        assertThat(detail.id).isEqualTo(1)
    }

}