package com.example.meetup.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meetup.authorization.AuthRepository
import com.example.meetup.authorization.AuthResult
import com.example.meetup.event.AuthUiEvent
import com.example.meetup.authorization.UserState
import com.example.meetup.authorization.dto.RegistrationRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    var state by mutableStateOf(UserState())

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()

    init {
        authenticate()
    }

    fun onEvent(event: AuthUiEvent) {
        when(event) {
            is AuthUiEvent.EmailChanged -> {
                state = state.copy(email = event.value)
            }
            is AuthUiEvent.PasswordChanged -> {
                state = state.copy(password = event.value)
            }
            is AuthUiEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.value)
            }
            is AuthUiEvent.LastNameChanged -> {
                state = state.copy(lastName = event.value)
            }
            is AuthUiEvent.SignIn -> {
                signIn()
            }
            is AuthUiEvent.SignUp -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signUp(
                RegistrationRequest(
                    firstName = state.firstName,
                    lastName = state.lastName,
                    email = state.email,
                    password = state.password
                )
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signIn(
                email = state.email,
                password = state.password
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun authenticate() {
        viewModelScope.launch {
//            state = state.copy(isLoading = true)
            val result = repository.authenticate()
            resultChannel.send(result)
//            state = state.copy(isLoading = false)
        }
    }
}