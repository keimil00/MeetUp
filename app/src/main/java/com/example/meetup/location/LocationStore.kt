package com.example.meetup.location

import android.content.Context
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

object LocationStore {
    // Singleton pattern to provide easy access to location everywhere
    lateinit var fusedLocationClient: FusedLocationProviderClient
    var storedLatitude: Double = 0.0
    var storedLongitude: Double = 0.0

    fun createStore(passsedContext: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(passsedContext)
        refreshLocation(passsedContext)
    }

    fun refreshLocation(passsedContext: Context) {
        val task = fusedLocationClient.lastLocation
        task.addOnSuccessListener {
            if (it != null) {
                storedLatitude = it.latitude
                storedLongitude = it.longitude
            } else {
                Toast.makeText(passsedContext, "Location null", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
