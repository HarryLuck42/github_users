package com.corp.luqman.githubusers.data.remote

import okhttp3.*


class TokenInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest: Request

        newRequest = request.newBuilder()
            .build()
        return chain.proceed(newRequest)
    }

}