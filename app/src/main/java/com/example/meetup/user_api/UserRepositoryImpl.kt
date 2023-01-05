package com.example.meetup.user_api

import android.content.SharedPreferences
import android.util.Base64
import com.example.meetup.authorization.dto.RegistrationRequest
import com.example.meetup.model.User
import retrofit2.HttpException

class UserRepositoryImpl(
    private val api: UserApi,
    private val prefs: SharedPreferences
): UserRepository {
    override suspend fun getUserInfo(): UserResult<Unit> {
        return try {
            api.getUserInfo()
            UserResult.CurrentLoggedUser()
        } catch (e: Exception) {
            UserResult.UnknownError()
        }
    }
}