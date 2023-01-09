package com.example.meetup.location

import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MarkerManager {
    companion object {
        fun createMarker(mapView: MapView, latitude: Double, longitude: Double, name: String, description: String): Marker {
            val marker = Marker(mapView)
            marker.setPosition(GeoPoint(latitude, longitude))
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            //marker.setIcon(context.resources.getDrawable(R.drawable.ic_gps_location))
            //marker.setInfoWindow()
            marker.title = name
            marker.snippet = description
            return marker
        }
    }
}