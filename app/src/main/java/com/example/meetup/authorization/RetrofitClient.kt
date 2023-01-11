package com.example.meetup.authorization

import com.example.meetup.api.AuthApi
import com.example.meetup.api.EventsApi
import com.example.meetup.api.FriendsApi
import com.example.meetup.user_api.UserApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject

class RetrofitClient @Inject constructor(
    private val authInterceptor: AuthInterceptor
) {
    val authApi: AuthApi by lazy {
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
    val friendsApi: FriendsApi by lazy {
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
    val userApi: UserApi by lazy {
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
    val eventsApi: EventsApi by lazy {
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