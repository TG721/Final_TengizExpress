package com.tengizMKCorp.tengizExpress.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key", "d3d5bca2b6mshf80f0cd7118810ap13f826jsn3ce5643bbfd1")
            .build()
        return chain.proceed(request)
    }
}