package com.example.meetup.authorization

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject

class RetrofitClient @Inject constructor(
    private val authInterceptor: AuthInterceptor
) {
    val api: AuthApi by lazy {
        Retrofit.Builder()
            .client(
                OkHttpClient()
                .newBuilder()
                .addInterceptor(authInterceptor)
                .build()
            )
            .baseUrl(HttpRoutes.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}