package com.example.meetup.screen

import android.annotation.SuppressLint
import android.media.Image
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.FabPosition
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meetup.R
import com.example.meetup.component.Drawer
import com.example.meetup.component.MapView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.meetup.authorization.dto.RegistrationRequest
import com.example.meetup.authorization.register
import com.example.meetup.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMeeting(navController: NavController) {
    Drawer(
        navController = navController,
        title = stringResource(id = R.string.new_meeting),
        content = { paddingValues ->

            var username by remember { mutableStateOf(TextFieldValue("")) }
            var password by remember { mutableStateOf(TextFieldValue("")) };
            var phoneNumber by remember { mutableStateOf(TextFieldValue("")) };
            var emailAddress by remember { mutableStateOf(TextFieldValue("")) };
            var name by remember { mutableStateOf(TextFieldValue("")) };
            var surname by remember { mutableStateOf(TextFieldValue("")) };
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Material 3")
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                ) { values ->
                    LazyColumn(contentPadding = values) {
                        items(1) {
                            Card(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,

                                    ),
                                shape = MaterialTheme.shapes.large
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {

                                    NewMeetingListItem(navController = navController, text = "Tytuł", onClick = {

                                    })

                                    NewMeetingListItem(navController = navController, text = "Osoby", onClick = {

                                    })

                                    NewMeetingListItem(navController = navController, text = "Data i godzina", onClick = {

                                    })

                                    NewMeetingListItem(navController = navController, text = "Opis", onClick = {

                                    })

                                    NewMeetingListItem(navController = navController, text = "Powiadomienie", onClick = {

                                    })

                                    NewMeetingListItem(navController = navController, text = "Kolor pinezki", onClick = {

                                    })
                                }
                            }
                        }
                    }
                }
            }
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMeetingListItem(navController: NavController, text: String, image: Image? = null, onClick: () -> Unit){ //optionally: user : User
    val imageUri = rememberSaveable{mutableStateOf("") }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,

            )
    ) {
        Column {
            ListItem(
                headlineText = { Text(text) },
                leadingContent = {
                    if (image == null) {
                        R.drawable.ic_user
                    } else {
                        image
                    }
                },
                modifier = Modifier.clickable {
                    onClick
                }
            )
        }
    }
   //Divider()
}











