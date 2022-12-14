package com.example.meetup.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meetup.R
import com.example.meetup.component.Drawer
import com.example.meetup.model.Friend
import com.example.meetup.navigation.Screen
import com.example.meetup.view_model.FriendsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Friends (navController: NavController, friendsViewModel: FriendsViewModel = hiltViewModel()) {
    LaunchedEffect(Unit, block = {
        friendsViewModel.getFriendsList()
    })

    val dialogState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
    if (dialogState.value) {
        Dialog(
            onDismissRequest = {
                dialogState.value = false
            },
            content = {
                AddFriendDialog("Add friend", dialogState)
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }



    Drawer(navController = navController,
        title = stringResource(id = R.string.friends),
        content = {
            Box(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .fillMaxSize()
            ){
                val notification = rememberSaveable{ mutableStateOf("") }
                if (notification.value.isNotEmpty()){
                    Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
                    notification.value = ""
                }
                FloatingActionButton(
                    onClick = { dialogState.value = true },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    shape = MaterialTheme.shapes.medium,
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add friend"
                        )
                    }
                )
                Column(modifier = Modifier
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
fun FriendListItem(navController: NavController, friend: Friend){
    val context = LocalContext.current
    Column {
        ListItem(
            headlineText = { Text(friend.firstName + " " + friend.surname) },
            supportingText = { Text(friend.emailAddress) },
            leadingContent = {
                // TODO replace this with proper icon
                Icon(
                    painter = painterResource(id = context.resources.getIdentifier("images${friend.id.toInt().mod(71)}", "drawable", context.packageName)),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(30.dp)
                )
            },
            modifier = Modifier.clickable {
                navController.navigate(
                    // TODO What can we show about another user?
                    route = Screen.Profile.route//(friend.emailAddress, friend.firstName, friend.surname),// + icon...
                    //, navigatorExtras =
                ) }
        )
        Divider()
    }
}
