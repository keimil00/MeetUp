package com.example.meetup.api

import com.example.meetup.authorization.HttpRoutes
import com.example.meetup.event.dto.NewEventRequestBody
import com.example.meetup.model.Event
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EventsApi {
    @POST(HttpRoutes.CREATE_EVENT)
    suspend fun createEvent(
        @Body request: NewEventRequestBody
    )

    @GET(HttpRoutes.GET_NEARBY_EVENTS)
    suspend fun getEvents(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): List<Event>
}