package com.example.meetup.view_model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meetup.UiText
import com.example.meetup.api.EventsApi
import com.example.meetup.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val api: EventsApi
): ViewModel() {
    private val _eventsList = mutableStateListOf<Event>()
    val eventsList: SnapshotStateList<Event>
        get() = _eventsList
    private val resultChannel = Channel<Boolean>()
    private val errorChannel = Channel<UiText>()
    val errorMessageChannel = errorChannel.receiveAsFlow()


    fun getEventsList() {
        viewModelScope.launch {
            try {
                val result = api.getEvents()
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

    // TODO add creating event

}