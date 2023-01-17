package com.example.meetup.vmtests

import com.example.meetup.MainCoroutineRule
import com.example.meetup.api.MockEventsApi
import com.example.meetup.view_model.EventViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals


@ExperimentalCoroutinesApi
class EventViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val viewModel = EventViewModel(MockEventsApi(initialList))

    @Test
    fun eventsViewModel_Initialization_listEmpty() {
        assertTrue(viewModel.eventsList.isEmpty())
    }

    @Test
    fun eventsViewModel_getValidId_correctEvent() {
        val foundEvent = viewModel.getEventById(initialList[1].id)
        assertEquals(foundEvent, initialList[1])
    }

    @Test
    fun eventsViewModel_createEvent_eventAddedToList() {
        viewModel.getEventsList(EXAMPLE_LAT, EXAMPLE_LON)
        val initialLen = viewModel.eventsList.size
        viewModel.createEvent(mockEventRequest, listOf(501))

        viewModel.getEventsList(EXAMPLE_LAT, EXAMPLE_LON)
        val newNames = viewModel.eventsList.map {it.name}

        assertTrue(newNames.contains(mockEventRequest.name))
        assertEquals(viewModel.eventsList.size, initialLen + 1)
    }

    companion object {
        val initialList = mutableListOf(mockEvent1, mockEvent2)
    }
}