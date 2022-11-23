package com.example.meetup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


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
    }
}


@Composable
fun MainScreen(login: String?) {
    Text(text = login ?: "User")
}
