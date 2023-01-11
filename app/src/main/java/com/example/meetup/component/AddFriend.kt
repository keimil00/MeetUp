@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.meetup.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meetup.R
import com.example.meetup.screen.BUTTON_FONT_SIZE
import com.example.meetup.view_model.FriendsViewModel

@Composable
fun AddFriendDialog(
    title: String,
    dialogState: MutableState<Boolean>,
    friendsViewModel: FriendsViewModel = hiltViewModel(),
) {
    val errorMessage = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(friendsViewModel, context) {
        friendsViewModel.addFriendResultChannel.collect {
            if (it) {
                dialogState.value = false
            }
        }
    }

    LaunchedEffect(friendsViewModel, context) {
        friendsViewModel.errorMessageChannel.collect {
            errorMessage.value = it.asString(context)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            TitleAndButton(title, dialogState)
            AddBody(email, errorMessage)
            BottomButtons(dialogState = dialogState, email = email)
        }
    }
}

@Composable
fun TitleAndButton(title: String, dialogState: MutableState<Boolean>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 24.sp)
            IconButton(modifier = Modifier.then(Modifier.size(24.dp)),
                onClick = {
                    dialogState.value = false
                }) {
                Icon(
                    Icons.Filled.Close,
                    "contentDescription"
                )
            }
        }
        Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 1.dp)
    }
}

@Composable
private fun BottomButtons(
    dialogState: MutableState<Boolean>,
    email: MutableState<String>,
    friendsViewModel: FriendsViewModel = hiltViewModel()
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                dialogState.value = false
            },
            modifier = Modifier
                .padding(10.dp)
                .width(140.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text(text = stringResource(id = R.string.cancel), fontSize = BUTTON_FONT_SIZE.sp)
        }
        Button(
            onClick = {
                friendsViewModel.addFriend(email.value)
            },
            modifier = Modifier
                .padding(10.dp)
                .width(130.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(text = stringResource(id = R.string.add), fontSize = BUTTON_FONT_SIZE.sp)
        }

    }
}

@Composable
private fun AddBody(email: MutableState<String>, errMsg: MutableState<String>) {
    Box(
        modifier = Modifier
            .padding(20.dp)
    ) {
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(text = stringResource(id = R.string.enter_email)) },
            supportingText = {
                Text(
                    text = errMsg.value,
                    color = MaterialTheme.colorScheme.error,
                )
            },
        )
    }
}
