package com.example.meetup.authorization

import android.content.SharedPreferences
import com.example.meetup.AppModule
import com.example.meetup.MeetUpApp
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val prefs: SharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = prefs.getString("jwt", null)
        val original = chain.request()
        val request: Request = if (token != null) {
            original.newBuilder()
                .header("Authorization", "Basic $token")
                .method(original.method, original.body)
                .build()
        } else {
            original.newBuilder()
                .method(original.method, original.body)
                .build()
        }
        return chain.proceed(request)
    }
}
