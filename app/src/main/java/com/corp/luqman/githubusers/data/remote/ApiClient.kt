package com.corp.luqman.githubusers.data.remote

import com.corp.luqman.githubusers.BuildConfig
import okhttp3.*


class TokenInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest: Request

        newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
            .build()
        return chain.proceed(newRequest)
    }

}