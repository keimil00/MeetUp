package com.example.meetup.vmtests

import com.example.meetup.event.dto.NewEventRequestBody
import com.example.meetup.model.Friend
import com.example.meetup.model.Event

const val SELECTION_INITIAL = false
const val SELECTION_TO_SET = true
const val EMAIL_TO_ADD = "jkowalski@gmail.com"

const val EXAMPLE_LAT = 51.1
const val EXAMPLE_LON = 17.7

val mockFriend1 = Friend("200", "Henryk", "Sienkiewicz", "henryk@sienkiewicz.pl")
val mockFriend2 = Friend("201", "Adam", "Mickiewicz", "adam@mickiewicz.pl")
val mockFriend3 = Friend("203", "Juliusz", "Słowacki", "juliusz@slowacki.pl")

val mockEvent1 = Event(100, "Event1", 500, "2023", 3600, 51.0, 17.0, "Description1", "Color1")
val mockEvent2 = Event(101, "Event2", 500, "2023", 3600, 51.0, 17.0, "Description2", "Color2")
val mockEventRequest = NewEventRequestBody("Event3", "2023", 3600, 51.0, 17.0, "Description3", "Color3")
