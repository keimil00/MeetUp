package com.example.meetup

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Date

data class User(
    val name: String,
    val username: String,
    val password: String,
    val image: Bitmap,
    val birthday: Date
    //val
    //val contentDescription: String
)
