package com.example.meetup.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meetup.api.FriendsApi
import com.example.meetup.authorization.AuthRepository
import com.example.meetup.model.Friend
import com.example.meetup.model.User
import com.example.meetup.user_api.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val api: UserApi
): ViewModel() {
    private var _currentLoggedUser = User(0,"","","","","",true,true,true,true,true,"")

    val currentUser: User
        get() = _currentLoggedUser

    fun getCurrentUser() {
        viewModelScope.launch {
            try {
                _currentLoggedUser = api.getUserInfo()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
