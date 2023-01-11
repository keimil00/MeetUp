package com.example.meetup.event.dto

// used for creating new event, has no id
// for getting events use model.Event
data class NewEventRequestBody(
    val name: String,
    val date: String,
    val durationInSeconds: Int,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val color: String
)
