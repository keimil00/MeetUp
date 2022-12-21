package com.example.meetup.authorization

import android.content.SharedPreferences
import android.util.Base64
import com.example.meetup.authorization.dto.RegistrationRequest
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
): AuthRepository {

    override suspend fun signUp(registrationRequest: RegistrationRequest): AuthResult<Unit> {
        return try {
            println("Before signup")

            api.signUp(
                request = registrationRequest
            )

            val token = Base64.encodeToString("${registrationRequest.email}:${registrationRequest.password}".toByteArray(), Base64.NO_WRAP)

            prefs.edit()
                .putString("jwt", token)
                .apply()

            println("After signup")
            println("Before token save")

            println("After token save")

            println("Before authenticate")
            // AuthResult.Authorized()
            authenticate()
//            signIn(username, password)
        } catch(e: HttpException) {
            if(e.code() == 401) {
                println(e.message())
                AuthResult.Unauthorized()
            } else {
                println(e.message())
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            println(e.message)
            println("Exception")

            AuthResult.UnknownError()
        }
    }

    override suspend fun signIn(email: String, password: String): AuthResult<Unit> {
        return try {
            val token = Base64.encodeToString("${email}:${password}".toByteArray(), Base64.NO_WRAP)
            api.authenticate("Basic $token")
            prefs.edit()
                .putString("jwt", token)
                .apply()
            AuthResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    // TODO when authentication will be ready on backend, correct this
    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.authenticate("Basic $token")
            println("After authenticate")
            AuthResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                println(e.message())
                AuthResult.Unauthorized()
            } else {
                println(e.message())
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            println(e.message)
            println("Exception")

            AuthResult.UnknownError()
        }
    }
}