package com.example.meetup.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object Login : Screen("login")
    object Register : Screen("activity_register")
    object Home : Screen("home")
    object Profile : Screen("profile")//{

    //  val friendUsername = "friendUsername"
    // val friendFirstName = "friendFirstName"
    //val friendSurname = "friendSurname"
    //val friendIconId = "friendIconId"
    // }
    object FriendProfile :
        Screen("profile/{friendUsername}/{friendFirstName}/{friendSurname}/{friendIconId}")

    object Friends : Screen("friends")
    object NewMeeting : Screen("new_meeting")
    object AboutApp : Screen("about_app")
    object EventDetails : Screen("event_details") {
        val eventId = "eventId"
    }

    // Add new object with new Activity as shown above

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}
