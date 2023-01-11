package com.example.meetup.location.permission


import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Common {
    companion object {
        fun checkIfPermissionGranted(context: Context, permission: String): Boolean {
            return (ContextCompat.checkSelfPermission(context, permission)
                    == PackageManager.PERMISSION_GRANTED)
        }

        fun shouldShowPermissionRationale(context: Context, permission: String): Boolean {
            val activity = context as Activity?

            return ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!,
                permission
            )
        }

        fun PackageManager.getMetadataKey(
            packageName: String,
            keyName: String
        ): String {
            val ai = this.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            val bundle = ai.metaData
            return bundle.getString(keyName)!!
        }


    }


}