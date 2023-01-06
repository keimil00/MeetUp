package com.example.meetup.model

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.icu.number.IntegerWidth
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Date

data class User(
    //val image: Bitmap?,
    //val birthday: Date?,

    val id: Int,
    val firstName: String ,
    val lastName: String ,
    val email: String ,
    val password: String ,
    val role: String ,
    val locked: Boolean,
    val enabled: Boolean,
    val accountNonLocked: Boolean,
    val credentialsNonExpired: Boolean,
    val accountNonExpired: Boolean,
    val username: String
    //val
    //val contentDescription: String
)
