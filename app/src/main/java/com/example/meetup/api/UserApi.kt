package com.example.meetup.api

import com.example.meetup.authorization.HttpRoutes
import retrofit2.http.GET

interface UserApi {

    @GET(HttpRoutes.USER_INFO)
    suspend fun getUserInfo(): User
}