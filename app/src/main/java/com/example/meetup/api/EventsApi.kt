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
    ): Int

    @GET(HttpRoutes.GET_NEARBY_EVENTS)
    suspend fun getEvents(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): List<Event>

    @POST(HttpRoutes.ADD_PARTICIPANTS)
    suspend fun addParticipants(
        @Query("eventId") eventId: Int,
        @Body participants: List<Int>
    )
}

const val MOCK_OWNER_ID = 230

class MockEventsApi(private var mockEventsList: MutableList<Event>) : EventsApi {

    override suspend fun createEvent(request: NewEventRequestBody): Int {
        val idToAdd = mockEventsList[mockEventsList.lastIndex].id + 1

        val newEvent = Event(
            idToAdd,
            request.name,
            MOCK_OWNER_ID,
            request.date,
            request.durationInSeconds,
            request.latitude,
            request.longitude,
            request.description,
            request.color
        )

        mockEventsList.add(newEvent)
        return idToAdd
    }

    override suspend fun getEvents(latitude: Double, longitude: Double): List<Event> {
        return mockEventsList
    }

    override suspend fun addParticipants(eventId: Int, participants: List<Int>) {
    }
}