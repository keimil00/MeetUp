package com.example.meetup.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meetup.api.FriendsApi
import com.example.meetup.authorization.AuthRepository
import com.example.meetup.model.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val api: FriendsApi
): ViewModel() {
    private val _friendsList = mutableListOf<Friend>()
    val fiendsList: List<Friend>
        get() = _friendsList

    fun getFriendsList() {
        viewModelScope.launch {
            try {
                _friendsList.clear()
                _friendsList.addAll(api.getFriends())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
