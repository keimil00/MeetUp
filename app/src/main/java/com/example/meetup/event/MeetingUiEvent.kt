package com.example.meetup.event

sealed class MeetingUiEvent {
    data class TitleChanged(val value: String) : AuthUiEvent()
    // TODO finnish when friends are finished
}
