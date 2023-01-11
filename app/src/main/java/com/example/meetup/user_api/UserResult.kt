package com.example.meetup.user_api

sealed class UserResult<T>(val data: T? = null) {
    class CurrentLoggedUser<T>(data: T? = null) : UserResult<T>(data)
    class UnknownError<T> : UserResult<T>()
}