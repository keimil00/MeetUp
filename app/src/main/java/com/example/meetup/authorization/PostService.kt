package com.example.meetup.authorization

import com.example.meetup.authorization.dto.RegistrationRequest
import com.example.meetup.authorization.dto.Token
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

interface PostService {
    suspend fun getConfirmation(): Token

    suspend fun  register(request: RegistrationRequest): Token?

    companion object {
        fun create(): PostService {
            return PostServiceImpl(
                client = HttpClient()
            )
        }
    }

}