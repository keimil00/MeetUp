package com.example.meetup.location

import android.content.Context
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

object ClickedLocationStore {
    // Singleton pattern to provide easy access to location where user pressed on map
    var clickedLatitude: Double = 0.0
    var clickedLongitude: Double = 0.0
    var wasClicked: Boolean = false
}