package com.example.meetup.user_api

import com.example.meetup.authorization.HttpRoutes
import com.example.meetup.model.User
import retrofit2.http.*

interface UserApi {
    @GET(HttpRoutes.USER_INFO)
    suspend fun getUserInfo(): User
}