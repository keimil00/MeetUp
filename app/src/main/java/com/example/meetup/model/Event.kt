package com.example.meetup.model

// used for getting events, has id
// for new events use NewEventRequestBody instead
data class Event(
    val id: Int,
    val name: String,
    val date: String,
    val durationInSeconds: Int,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val color: String
)
