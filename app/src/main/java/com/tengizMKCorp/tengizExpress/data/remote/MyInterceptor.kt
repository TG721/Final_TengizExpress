package com.tengizMKCorp.tengizExpress.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key", "94b211b405msh323602ced6f1662p1e8b5ejsn669c28338831")
            .build()
        return chain.proceed(request)
    }
}