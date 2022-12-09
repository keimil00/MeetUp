package com.example.meetup.authorization

import com.example.meetup.authorization.dto.RegistrationRequest
import com.example.meetup.authorization.dto.Token
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*



class PostServiceImpl(
    private val client: HttpClient
): PostService {
    override suspend fun getConfirmation(): Token {
        return try {
            client.get {
                url(HttpRoutes.CONFIRM)
            }.body()
        } catch (e: Exception) {
            print(e.stackTrace)
            throw e
        }
    }

    @OptIn(InternalAPI::class)
    override suspend fun register(request: RegistrationRequest): Token? {
        return try {
            client.post{
                url(HttpRoutes.REGISTRATION)
                contentType(ContentType.Application.Json)
                body = request
            }.body()
        } catch (e: Exception) {
            print(e.stackTrace)
            throw e
        }
    }
}
