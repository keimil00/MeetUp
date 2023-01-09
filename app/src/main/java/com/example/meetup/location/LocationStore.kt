package com.example.meetup.location

import android.content.Context
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationStore(passsedContext: Context) {
    lateinit var fusedLocationClient: FusedLocationProviderClient
    var storedLatitude: Double = 0.0
    var storedLongitude: Double = 0.0

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(passsedContext)
        refreshLocation(passsedContext)
    }

    fun refreshLocation(passsedContext: Context) {
        val task = fusedLocationClient.lastLocation
        task.addOnSuccessListener {
            if (it != null) {
                storedLatitude = it.latitude
                storedLongitude = it.longitude
            }
            else {
                Toast.makeText(passsedContext, "Location null", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//
//    private fun fetchLocation() {
//        val task = fusedLocationClient.lastLocation
//
//        task.addOnSuccessListener {
//            if (it != null){
//                Toast.makeText(applicationContext, "Lat is: ${it.latitude}, Lon is: ${it.longitude}", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                Toast.makeText(applicationContext, "Location null", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}