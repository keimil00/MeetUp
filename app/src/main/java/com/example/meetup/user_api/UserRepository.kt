package com.example.meetup.user_api

import com.example.meetup.model.User

interface UserRepository {
    suspend fun getUserInfo(): UserResult<Unit>;

}