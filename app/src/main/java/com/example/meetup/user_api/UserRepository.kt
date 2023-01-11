package com.example.meetup.user_api

interface UserRepository {
    suspend fun getUserInfo(): UserResult<Unit>

}