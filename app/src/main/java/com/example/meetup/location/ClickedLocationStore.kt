package com.example.meetup.location

object ClickedLocationStore {
    // Singleton pattern to provide easy access to location where user pressed on map
    var clickedLatitude: Double = 0.0
    var clickedLongitude: Double = 0.0
    var wasClicked: Boolean = false
}