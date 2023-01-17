package com.example.meetup.vmtests

import com.example.meetup.model.FriendSelect
import com.example.meetup.view_model.ParticipantsViewModel
import org.junit.Assert.assertTrue
import org.junit.Test

class ParticipantsViewModelTest {
    // Based on official tutorial:
    // https://developer.android.com/codelabs/basic-android-kotlin-compose-test-viewmodel

    private val viewModel = ParticipantsViewModel()

    @Test
    fun participantsViewModel_Initialization_ListEmpty() {
        val initialList = viewModel.participantsList

        assertTrue(initialList.isEmpty())
    }

    @Test
    fun participantsViewModel_AddingRemoving_ListCorrect() {

        val mockList = listOf(
            FriendSelect(mockFriend1, false),
            FriendSelect(mockFriend2, false)
        )
        viewModel.setParticipantsList(mockList)

        assertTrue(viewModel.participantsList.contains(FriendSelect(mockFriend1, false)))
        assertTrue(viewModel.participantsList.contains(FriendSelect(mockFriend2, false)))

        viewModel.clearParticipantsList()

        assertTrue(viewModel.participantsList.isEmpty())
    }


}