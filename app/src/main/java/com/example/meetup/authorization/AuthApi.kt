package com.example.meetup.authorization

import com.example.meetup.authorization.dto.RegistrationRequest
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST(HttpRoutes.REGISTRATION)
    suspend fun signUp(
        @Body request: RegistrationRequest
    )

    @GET(HttpRoutes.CONFIRM)
    suspend fun authenticate(
        @Header("Authorization") token: String
    )
}