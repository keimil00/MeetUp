package com.example.meetup.vmtests

import com.example.meetup.MainCoroutineRule
import com.example.meetup.api.MockFriendsApi
import com.example.meetup.view_model.FriendsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Rule

@ExperimentalCoroutinesApi
class FriendsViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val viewModel = FriendsViewModel(MockFriendsApi(initialList))

    @Test
    fun friendsViewModel_Initialization_ListEmpty() {
        assertTrue(viewModel.friendsList.isEmpty())
    }

    @Test
    fun friendsViewModel_getValidID_correctFriend() {
        // check getting friend by id
        val foundFriend = viewModel.getFriendById(initialList[1].id.toInt())

        assertEquals(foundFriend, initialList[1])
    }

    @Test
    fun friendsViewModel_addFriend_FriendAddedToList() {
        // check if adding a new friend to the list works as intended
        viewModel.getFriendsList()
        val initialLen = viewModel.friendsList.size
        viewModel.addFriend(EMAIL_TO_ADD)

        viewModel.getFriendsList()
        val newEmails = viewModel.friendsList.map {it.emailAddress}

        assertTrue(newEmails.contains(EMAIL_TO_ADD))
        assertEquals(viewModel.friendsList.size, initialLen + 1)
    }

    companion object {
       // val initialList = mutableListOf(mockFriend1, mockFriend2, mockFriend3)
    }
}