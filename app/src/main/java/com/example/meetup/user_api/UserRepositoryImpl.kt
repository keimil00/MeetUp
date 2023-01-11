package com.example.meetup.user_api

import android.content.SharedPreferences

class UserRepositoryImpl(
    private val api: UserApi,
    private val prefs: SharedPreferences
) : UserRepository {
    override suspend fun getUserInfo(): UserResult<Unit> {
        return try {
            api.getUserInfo()
            UserResult.CurrentLoggedUser()
        } catch (e: Exception) {
            UserResult.UnknownError()
        }
    }
}