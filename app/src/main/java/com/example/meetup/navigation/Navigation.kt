package com.example.meetup.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meetup.model.Friend
import com.example.meetup.screen.*
import kotlin.reflect.typeOf


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) {
            Login(navController = navController)
        }
        composable(
            route = Screen.MainScreen.route + "/{login}",
            arguments = listOf(
            navArgument("login") {
                type = NavType.StringType
                defaultValue = "User"
                nullable = true
            }
            )
        ) {
            entry -> MainScreen(entry.arguments?.getString("login"))
        }
        composable(route = Screen.Register.route) {
            Register(navController = navController)
        }
        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }
        composable(route = Screen.Profile.route,
            arguments = listOf(
                navArgument("friendUsername"){
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("friendFirstName"){
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("friendSurname"){
                    type = NavType.StringType
                    nullable = true
                }
            )
            ){
                navBackStackEntry ->
            val args = navBackStackEntry.arguments
            val friendUsername = args?.getString("eventId")
            val friendFirstname = args?.getString("friendFirstName")
            val friendSurname = args?.getString("friendSurname")

            Profile(navController = navController, friendUsername, friendFirstname, friendSurname)
        }
        composable(route = Screen.Friends.route){
            Friends(navController = navController)
        }
        composable(route = Screen.NewMeeting.route){
            NewMeeting(navController = navController)
        }
        composable(route = Screen.AboutApp.route){
            AboutApp(navController = navController)
        }
        composable(
            route = Screen.EventDetails.route + "/{eventId}",
            arguments = listOf(
                navArgument("eventId") {
                    type = NavType.IntType  // TODO maybe Reference type - check how it works - SZ
                }
            )
            ){
            navBackStackEntry ->
            val args = navBackStackEntry.arguments
            val eventId = args?.getInt("eventId")!!

            EventDetails(navController = navController, eventId = eventId)
        }

        // Add new composable with route which is path to a screen and optionally arguments as above
    }
}


@Composable
fun MainScreen(login: String?) {
    Text(text = login ?: "User")
}
