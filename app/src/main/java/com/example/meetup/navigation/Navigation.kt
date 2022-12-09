package com.example.meetup.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meetup.screen.*


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
        composable(route = Screen.ActivityRegister.route) {
            ActivityRegister(navController = navController)
        }
        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }
        composable(route = Screen.Profile.route){
            Profile(navController = navController)
        }
        composable(route = Screen.Friends.route){
            Friends(navController = navController)
        }

        // Add new composable with route which is path to a screen and optionally arguments as above
    }
}


@Composable
fun MainScreen(login: String?) {
    Text(text = login ?: "User")
}
