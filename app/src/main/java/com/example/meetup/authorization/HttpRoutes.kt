package com.example.meetup.authorization

object HttpRoutes {
    const val BASE_URL = "http://130.61.27.111:8080"
    const val REGISTRATION = "$BASE_URL/registration"
    // TODO when there will test or confirm endpoint on backend add path here
    const val CONFIRM = "$BASE_URL/users/current"
    const val GET_FRIENDS = "$BASE_URL/get_friends"
    const val ADD_FRIENDS = "$BASE_URL/add_friend"
    const val USER_INFO = "$BASE_URL/users/current"
    const val GET_NEARBY_EVENTS = "$BASE_URL/get_nearby_events"
    const val CREATE_EVENT = "$BASE_URL/create_event"
}