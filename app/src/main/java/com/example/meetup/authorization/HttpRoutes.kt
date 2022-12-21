package com.example.meetup.authorization

object HttpRoutes {
    const val BASE_URL = "http://130.61.27.111:8080"
    const val REGISTRATION = "$BASE_URL/registration"
    // TODO when there will test or confirm endpoint on backend add path here
    const val CONFIRM = "$BASE_URL/users/current"
}