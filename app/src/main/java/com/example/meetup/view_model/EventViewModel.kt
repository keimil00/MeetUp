package com.example.meetup.view_model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meetup.UiText
import com.example.meetup.api.EventsApi
import com.example.meetup.event.dto.NewEventRequestBody
import com.example.meetup.location.LocationStore
import com.example.meetup.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val api: EventsApi
) : ViewModel() {
    private val _eventsList = mutableStateListOf<Event>()
    val eventsList: SnapshotStateList<Event>
        get() = _eventsList
    private val resultChannel = Channel<Boolean>()
    private val errorChannel = Channel<UiText>()
    val errorMessageChannel = errorChannel.receiveAsFlow()


    fun getEventsList(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                val result = api.getEvents(latitude = latitude, longitude = longitude)
                result.forEach {
                    if (!_eventsList.contains(it)) {
                        _eventsList.add(it)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createEvent(request: NewEventRequestBody, participants: List<Int>) {
        viewModelScope.launch {
            try {
                val result = api.createEvent(request)
                addParticipants(result, participants)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getEventById(searchedID: Int?): Event? {
        // made because of lack of endpoint in api
        getEventsList(LocationStore.storedLatitude, LocationStore.storedLongitude)
        return eventsList.firstOrNull { it.id == searchedID }
    }

    private fun addParticipants(eventId: Int, participants: List<Int>) {
        viewModelScope.launch {
            try {
                api.addParticipants(eventId, participants)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}