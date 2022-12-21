package com.example.meetup

import android.app.Application
import android.content.Context

public class MeetUp : Application() {
    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {
        var instance: MeetUp? = null
            private set

        // or return instance.getApplicationContext();
        val context: Context?
            get() = instance
        // or return instance.getApplicationContext();
    }
}