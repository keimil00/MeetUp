package com.example.meetup.view_model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.meetup.model.FriendSelect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ParticipantsViewModel @Inject constructor() : ViewModel() {
    private val _participantsList = mutableStateListOf<FriendSelect>()
    val participantsList: List<FriendSelect>
        get() = _participantsList

    fun setParticipantsList(participants: List<FriendSelect>) {
        _participantsList.clear()
        _participantsList.addAll(participants)
    }

    fun setParticipantSelected(friendToSelect: FriendSelect, isSelected: Boolean) {
        val index = _participantsList.indexOf(friendToSelect)
        if (index != -1) {
            val friend = _participantsList[index].copy(isSelected = isSelected)
            _participantsList[index] = friend
        }
    }

    fun clearParticipantsList() {
        _participantsList.clear()
    }
}