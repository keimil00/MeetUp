package com.example.meetup.component

import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.util.GeoPoint

class MeetupMapEventReceiver: MapEventsReceiver  {
    override fun longPressHelper(p: GeoPoint?): Boolean {
        TODO("Not yet implemented")
    }

    override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
        TODO("Not yet implemented")
    }
}