package com.example.meetup.navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object Login : Screen("login")
    object Register: Screen("activity_register")
    object Home: Screen("home")
    object Profile: Screen("profile")
    object Friends: Screen("friends")
    object NewMeeting: Screen("new_meeting")

    // Add new object with new Activity as shown above

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
