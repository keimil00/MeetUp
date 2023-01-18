package com.example.meetup.user_api

import com.example.meetup.api.FriendsApi
import com.example.meetup.authorization.HttpRoutes
import com.example.meetup.model.Friend
import com.example.meetup.model.User
import retrofit2.http.*

interface UserApi {
    @GET(HttpRoutes.USER_INFO)
    suspend fun getUserInfo(): User
}

class MockUserApi() : UserApi {
    override suspend fun getUserInfo(): User {
        return User(1,
            "Jan",
            "Kowalski",
            "j.kow@gmail.com",
            "haslo",
            "user",
            false,
            true,
            true,
            true,
            true,
            "j.kowalski"
        )
    }
}