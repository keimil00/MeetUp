package com.example.meetup.vmtests

import com.example.meetup.model.FriendSelect
import com.example.meetup.view_model.ParticipantsViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ParticipantsViewModelTest {
    // Based on official tutorial:
    // https://developer.android.com/codelabs/basic-android-kotlin-compose-test-viewmodel

    private val viewModel = ParticipantsViewModel()

    @Test
    fun participantsViewModel_Initialization_ListEmpty() {
        // Boundary case
        val initialList = viewModel.participantsList

        assertTrue(initialList.isEmpty())
    }

    @Test
    fun participantsViewModel_AddingRemoving_ListCorrect() {
        viewModel.setParticipantsList(mockList)

        assertTrue(viewModel.participantsList.contains(FriendSelect(mockFriend1, SELECTION_INITIAL)))
        assertTrue(viewModel.participantsList.contains(FriendSelect(mockFriend2, SELECTION_INITIAL)))

        viewModel.clearParticipantsList()

        assertTrue(viewModel.participantsList.isEmpty())
    }

    @Test
    fun participantsViewModel_selectingValid_friendSelected() {
        // Success Path
        viewModel.setParticipantsList(mockList)

        viewModel.setParticipantSelected(mockList[0], SELECTION_TO_SET)

        assertTrue(viewModel.participantsList[0].isSelected == SELECTION_TO_SET)
    }

    @Test
    fun participantsViewModel_selectingInvalid_friendNotSelected() {
        // Error path
        viewModel.setParticipantsList(mockList)
        val invalidFriend = FriendSelect(mockFriend3, SELECTION_INITIAL)
        viewModel.setParticipantSelected(invalidFriend, SELECTION_TO_SET)

        for (part in viewModel.participantsList) {
            assertFalse(part.isSelected == SELECTION_TO_SET)
        }
    }

    companion object {
        val mockList = listOf(
            FriendSelect(mockFriend1, SELECTION_INITIAL),
            FriendSelect(mockFriend2, SELECTION_INITIAL)
        )
    }
}