package com.example.meetup.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meetup.api.FriendsApi
import com.example.meetup.model.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val api: FriendsApi
): ViewModel() {
    private val _friendsList = mutableListOf<Friend>()
    val fiendsList: List<Friend>
        get() = _friendsList.toList()
    private val resultChannel = Channel<Boolean>()
    val addFriendResultChannel = resultChannel.receiveAsFlow()



    fun getFriendsList() {
        viewModelScope.launch {
            try {
                val result = api.getFriends()
                result.forEach {
                    if (!_friendsList.contains(it)) {
                        _friendsList.add(it)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addFriend(email: String) {
        viewModelScope.launch {
            try {
                api.addFriend(email)
                resultChannel.send(true)
                waitForResult()
            } catch (e: Exception) {
                e.printStackTrace()
                resultChannel.send(false)
            }
        }
    }

    private suspend fun waitForResult() {
        withContext(Dispatchers.IO) {
            val size = _friendsList.size
            while (true) {
                getFriendsList()
                if (_friendsList.size > size) {
                    resultChannel.send(true)
                    break
                }
                Thread.sleep(300)
            }
        }
    }
}
