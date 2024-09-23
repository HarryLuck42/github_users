package com.corp.luqman.githubusers.di.hilt

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.corp.luqman.githubusers.BuildConfig
import com.corp.luqman.githubusers.data.remote.ApiService
import com.corp.luqman.githubusers.data.remote.TokenInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String{
        return BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext appContext: Context) : OkHttpClient{
        val httpClient = OkHttpClient.Builder()
        httpClient.apply {
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            callTimeout(60, TimeUnit.SECONDS)
            addInterceptor(TokenInterceptor())
            addInterceptor(ChuckerInterceptor.Builder(appContext)
                .collector(ChuckerCollector(appContext))
                .build())
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(logging)
        }
        return httpClient.build()
    }


    @Singleton
    @Provides
    fun provideRetrofitApi(url: String,
                           client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}