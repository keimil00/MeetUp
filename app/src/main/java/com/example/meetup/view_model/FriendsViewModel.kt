package com.example.meetup.view_model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meetup.R
import com.example.meetup.UiText
import com.example.meetup.api.FriendsApi
import com.example.meetup.model.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val api: FriendsApi
): ViewModel() {
    private val _friendsList = mutableStateListOf<Friend>()
    val friendsList: SnapshotStateList<Friend>
        get() = _friendsList
    private val resultChannel = Channel<Boolean>()
    val addFriendResultChannel = resultChannel.receiveAsFlow()
    private val errorChannel = Channel<UiText>()
    val errorMessageChannel = errorChannel.receiveAsFlow()


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
                async { waitForResult() }
            }catch (e: HttpException) {
                errorChannel.send(handleError(e.code()))
                resultChannel.send(false)
            }
            catch (e: Exception) {
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
                delay(300)
            }
        }
    }

    private fun handleError(errCode: Int): UiText {
        return when (errCode){
            404 -> {
                UiText.StringResource(R.string.user_not_found)
            }
            409 -> {
                UiText.StringResource(R.string.already_friends)
            }
            else -> {
                UiText.StringResource(R.string.error_occurred)
            }
        }
    }

    fun getFriendById(searchedID: Int?): Friend? {
        // made because of lack of endpoint in api
        getFriendsList()

        return fiendsList.firstOrNull { it.id.toInt() == searchedID }
    }
}
