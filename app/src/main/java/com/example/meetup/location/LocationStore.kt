package com.example.meetup.location

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

//class LocationStore(passsedContext: Context)  {
//    lateinit var fusedLocationClient: FusedLocationProviderClient
//
//    init {
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(passsedContext)
//    }
//
//    fun lastLatitude(): Double {
//        val task = fusedLocationClient.lastLocation
//        task.addOnSuccessListener {
//            if (it != null){
//                return@addOnSuccessListener
//                it.latitude
//            }
//
//            return@addOnSuccessListener
//            77.7
//
//        }
//    }
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