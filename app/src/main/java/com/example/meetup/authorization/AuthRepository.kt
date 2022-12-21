package com.example.meetup.authorization

import com.example.meetup.authorization.dto.RegistrationRequest

interface AuthRepository {
    suspend fun signUp(registrationRequest: RegistrationRequest): AuthResult<Unit>
    suspend fun signIn(username: String, password: String): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
}