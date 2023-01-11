package com.example.meetup.component

import androidx.navigation.NavController
import com.example.meetup.location.ClickedLocationStore
import com.example.meetup.navigation.Screen
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.util.GeoPoint

class MeetupMapEventReceiver(var navController: NavController): MapEventsReceiver  {
    override fun longPressHelper(p: GeoPoint?): Boolean {
        ClickedLocationStore.wasClicked = true
        ClickedLocationStore.clickedLatitude = p?.latitude ?: 1.23
        ClickedLocationStore.clickedLongitude = p?.longitude ?: 1.23

        navController.navigate(
            Screen.NewMeeting.route
        )
        return true
    }

    override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
        return false
    }
}