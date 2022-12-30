package com.example.meetup.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meetup.R
import com.example.meetup.component.Drawer
import com.example.meetup.model.Friend
import com.example.meetup.navigation.Screen
import com.example.meetup.view_model.FriendsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Friends (navController: NavController, friendsViewModel: FriendsViewModel = hiltViewModel()) {
    LaunchedEffect(Unit, block = {
        friendsViewModel.getFriendsList()
    })
    Drawer(navController = navController,
        title = stringResource(id = R.string.friends),
        content = {
            Box(modifier = Modifier.padding(paddingValues = it)){
                val notification = rememberSaveable{ mutableStateOf("") }
                if (notification.value.isNotEmpty()){
                    Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
                    notification.value = ""
                }

                Column(modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp)
                ){
                    LazyColumn(modifier = Modifier.fillMaxWidth()){
                        items(friendsViewModel.fiendsList) {
                            friend -> FriendListItem(navController = navController, friend = friend)
                        }
                    }
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendListItem(navController: NavController, friend: Friend){ //optionally: user : User
    Column {
        ListItem(
            headlineText = { Text(friend.firstName) },
            supportingText = { Text(friend.surname) },
            leadingContent = {
                // TODO replace this with proper icon
                Icon(
                    Icons.Filled.Favorite,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(30.dp)
                )
            },
            modifier = Modifier.clickable {
                navController.navigate(
                    // TODO What can we show about another user?
                    Screen.Profile.route//(user)
                ) }
        )
        Divider()
    }
}
