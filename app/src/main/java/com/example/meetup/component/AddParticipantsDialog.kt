package com.example.meetup.component

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meetup.R
import com.example.meetup.model.FriendSelect
import com.example.meetup.screen.BUTTON_FONT_SIZE
import com.example.meetup.view_model.FriendsViewModel
import com.example.meetup.view_model.ParticipantsViewModel

@Composable
fun AddParticipantsDialog(
    dialogState: MutableState<Boolean>,
    friendsViewModel: FriendsViewModel = hiltViewModel(),
    participantsViewModel: ParticipantsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(friendsViewModel, context) {
        friendsViewModel.getFriendsList()
    }
    if (participantsViewModel.participantsList.isEmpty()) {
        participantsViewModel.setParticipantsList(friendsViewModel.friendsList.map {
            FriendSelect(
                it,
                false
            )
        })
    }
    Card(
        modifier = Modifier
//            .padding(horizontal = 8.dp, vertical = 32.dp)
            .fillMaxWidth()
            .height(600.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            TitleAndButton(stringResource(id = R.string.add_participants), dialogState)
            AddBody(participantsViewModel)
            BottomButtons(dialogState = dialogState)
        }
    }
}

@Composable
private fun AddBody(participantsViewModel: ParticipantsViewModel) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .height(425.dp)
    ) {
        items(participantsViewModel.participantsList) {
            FriendSelectItem(friendSelect = it, participantsViewModel = participantsViewModel)
        }
    }
}

@SuppressLint("DiscouragedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendSelectItem(friendSelect: FriendSelect, participantsViewModel: ParticipantsViewModel) {
    val context = LocalContext.current
    ListItem(
        headlineText = { Text(friendSelect.friend.firstName + " " + friendSelect.friend.surname) },
        supportingText = { Text(friendSelect.friend.emailAddress) },
        leadingContent = {
            Icon(
                painter = painterResource(
                    id = context.resources.getIdentifier(
                        "images${friendSelect.friend.id.toInt().mod(71)}",
                        "drawable", context.packageName
                    )
                ),
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = "Localized description",
                modifier = Modifier.size(30.dp)
            )
        },
        trailingContent = {
            Checkbox(
                checked = friendSelect.isSelected,
                onCheckedChange = {
                    participantsViewModel.setParticipantSelected(
                        friendSelect,
                        it
                    )
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.onBackground,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                participantsViewModel.setParticipantSelected(friendSelect, !friendSelect.isSelected)
            },
    )
    Divider()
}

@Composable
private fun BottomButtons(dialogState: MutableState<Boolean>) {
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
                .padding(horizontal = 10.dp)
                .width(110.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
        ) {
            Text(text = stringResource(id = R.string.cancel), fontSize = BUTTON_FONT_SIZE.sp)
        }
        Button(
            onClick = {
                dialogState.value = false
            },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .width(110.dp),
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

