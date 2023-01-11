package com.example.meetup.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meetup.R
import com.example.meetup.component.Drawer
import com.example.meetup.view_model.EventViewModel
import com.example.meetup.view_model.FriendsViewModel
import com.example.meetup.view_model.UserViewModel
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun EventDetails(
    navController: NavController,
    eventId: Int,
    eventViewModel: EventViewModel = hiltViewModel(),
    friendsViewModel: FriendsViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit, block = {
        userViewModel.getCurrentUser()
    })
    val displayedEvent = eventViewModel.getEventById(eventId)

    var ownerFirstName: String
    var ownerSurname: String
    var ownerId = displayedEvent?.ownerId
    var selfCreated: Boolean

    // Check if event was created by this user, or his friend
    if (displayedEvent?.ownerId == userViewModel.currentUser.id) {
        ownerFirstName = userViewModel.currentUser.firstName
        ownerSurname = userViewModel.currentUser.lastName
        selfCreated = true
    }
    else {
        ownerFirstName = friendsViewModel.getFriendById(displayedEvent?.ownerId)?.firstName ?: ""
        ownerSurname = friendsViewModel.getFriendById(displayedEvent?.ownerId)?.firstName ?: ""
        selfCreated = false
    }
    var formattedDuration: String
    if (displayedEvent?.durationInSeconds != null) {
        formattedDuration = String.format(
            "This event will last for %02d:%02d",
            displayedEvent?.durationInSeconds / 3600,
            (displayedEvent?.durationInSeconds % 3600) / 60,
        )
    }
    else {
        formattedDuration = String.format(
            "This event will last for %02d:%02d",
            0,
            30,
        )
    }

    Drawer(navController = navController, title = stringResource(id = R.string.event_details)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .padding(bottom = 20.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                val notification = rememberSaveable { mutableStateOf("") }
                if (notification.value.isNotEmpty()) {
                    Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG)
                        .show()
                    notification.value = ""
                }

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            // Fuszera - if null display empty string (looks OK in app)
                            text = displayedEvent?.name ?: "",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 40.sp,
                            lineHeight = 40.sp,
                        )

                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = displayedEvent?.description ?: "",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (selfCreated) {
                            Text(
                                text = "You created this event",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        else {
                            Text(
                                text = "${ownerFirstName} ${ownerSurname} invited you",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = displayedEvent?.date ?: "",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = formattedDuration,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                }
            }
        }
    }
}

//                    Button(
//                        onClick = {
//                            // TODO go to map and locate this marker
//                        },
//                        modifier = Modifier
//                            .padding(
//                                horizontal = BUTTON_HORIZONTAL_PADDING.dp,
//                                vertical = 10.dp
//                            )
//                            .fillMaxWidth()
//                            .height(BUTTON_HEIGHT.dp)
//                    ) {
//                        Text(text = "Locate on map", fontSize = BUTTON_FONT_SIZE.sp)
//                    }
