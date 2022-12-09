package com.example.meetup.model

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val title: String,
    val icon: ImageVector,
    val navRoute: String = ""
)
