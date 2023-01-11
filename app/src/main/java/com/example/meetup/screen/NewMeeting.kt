package com.example.meetup.screen

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meetup.R
import com.example.meetup.component.Drawer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import com.chargemap.compose.numberpicker.FullHours
import com.chargemap.compose.numberpicker.Hours
import com.chargemap.compose.numberpicker.HoursNumberPicker
import com.example.meetup.event.dto.NewEventRequestBody
import com.example.meetup.location.LocationStore
import com.example.meetup.navigation.Screen
import com.example.meetup.view_model.EventViewModel
import com.example.meetup.view_model.UserViewModel
import com.github.skydoves.colorpicker.compose.*
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.color.ColorPalette
import com.vanpra.composematerialdialogs.color.colorChooser
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import io.ktor.server.application.*
import io.ktor.server.http.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMeeting(navController: NavController, eventViewModel: EventViewModel = hiltViewModel()) {
    LocationStore.refreshLocation(LocalContext.current)

    Drawer(
        navController = navController,
        title = stringResource(id = R.string.new_meeting),
        content = { paddingValues ->
            var title by remember { mutableStateOf("") }
            //var participants...
            //var latitude : Double
            //var longitude : Double
            var pickedDate by remember { mutableStateOf(LocalDate.now()) }
            var pickedTime by remember { mutableStateOf(LocalTime.now()) }
            var description by remember { mutableStateOf("") }
            var pickedDurationTime by remember { mutableStateOf<Hours>(FullHours(0, 30)) }
            var pickedColor by remember { mutableStateOf(Color(0xFFF44336)) }

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

                                    // made only to test location reading
//                                    Button(onClick = {
//                                        context.startActivity(Intent(context, LocationActivity::class.java))
//                                    }) {
//                                        Text(text = "Test location")
//                                    }
                                    // ---------

//Title
                                    TextField(
                                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                                        value = title,
                                        onValueChange = {
                                            title = it
                                        },
                                        label = { Text(text = "Title") },
                                        placeholder = { Text(text = "Provide the title of the meeting") },
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(20.dp))
                                    )
//Participants
                                    NewMeetingListItem(
                                        navController = navController,
                                        text = "Participants...",
                                        onClick = {
                                            // TODO: navigate to "add participants screen" or sth similar
                                        })

                                    Spacer(modifier = Modifier.height(16.dp))
//Date/Time pickers
                                    val formattedDate by remember {
                                        derivedStateOf {
                                            DateTimeFormatter
                                                .ofPattern("MMM dd yyyy")
                                                .format(pickedDate)
                                        }
                                    }
                                    val formattedTime by remember {
                                        derivedStateOf {
                                            DateTimeFormatter
                                                .ofPattern("hh:mm")
                                                .format(pickedTime)
                                        }
                                    }

                                    val dateDialogState = rememberMaterialDialogState()
                                    val timeDialogState = rememberMaterialDialogState()

                                    Row(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth(0.47f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Button(onClick = {
                                                dateDialogState.show()
                                            }) {
                                                Text(text = "Pick date")
                                            }
                                            Text(text = formattedDate)
                                        }

                                        Column(
                                            modifier = Modifier.fillMaxWidth(0.9f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Button(onClick = {
                                                timeDialogState.show()
                                            }) {
                                                Text(text = "Pick time")
                                            }
                                            Text(text = formattedTime)
                                        }
                                    }

                                    MaterialDialog(
                                        dialogState = dateDialogState,
                                        buttons = {
                                            positiveButton(text = "ok") // TODO remove weird purple color
                                            negativeButton(text = "Cancel")
                                        }
                                    ) {
                                        datepicker(
                                            initialDate = LocalDate.now(),
                                            title = "Pick a date",
                                            colors = DatePickerDefaults.colors(
                                                headerBackgroundColor = MaterialTheme.colorScheme.primary,
                                                //headerTextColor: Color,
                                                //calendarHeaderTextColor: Color,
                                                dateActiveBackgroundColor = MaterialTheme.colorScheme.primary,
                                                //dateInactiveBackgroundColor: Color,
                                                //dateActiveTextColor: Color,
                                                //dateInactiveTextColor: Color
                                            ),
                                        ) {
                                            pickedDate = it;
                                        }
                                    }
                                    MaterialDialog(
                                        dialogState = timeDialogState,
                                        buttons = {
                                            positiveButton(text = "ok")// TODO remove weird purple color
                                            negativeButton(text = "Cancel")
                                        },
                                        backgroundColor = MaterialTheme.colorScheme.background,
                                    ) {
                                        timepicker(
                                            initialTime = LocalTime.NOON,
                                            title = "Pick time",
                                            colors = TimePickerDefaults.colors(
                                                activeBackgroundColor = MaterialTheme.colorScheme.primary,
                                                //inactiveBackgroundColor: Color,
                                                activeTextColor = Color.White,
                                                inactiveTextColor = MaterialTheme.colorScheme.onBackground,
                                                //inactivePeriodBackground: Color,
                                                selectorColor = MaterialTheme.colorScheme.primary,
                                                selectorTextColor = Color.White,
                                                headerTextColor = Color.White,
                                                borderColor = Color.White
                                            ),
                                        ) {
                                            pickedTime = it;
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
//Description
                                    TextField(
                                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                                        value = description,
                                        onValueChange = {
                                            description = it
                                        },
                                        label = { Text(text = "Description") },
                                        placeholder = { Text(text = "Add some notes") },
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(20.dp))
                                        //.background(Color.White)
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
//Duration
                                    Card(
                                        modifier = Modifier
                                            .padding(start = 32.dp)
                                            .fillMaxWidth(),
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                        )
                                    ) {
                                        Text(text = "Duration of meeting")
                                    }

                                    HoursNumberPicker(
                                        leadingZero = true,
                                        value = pickedDurationTime,
                                        onValueChange = {
                                            pickedDurationTime = it
                                        },
                                        hoursDivider = {
                                            Text(
                                                modifier = Modifier.size(24.dp),
                                                textAlign = TextAlign.Center,
                                                text = "h"
                                            )
                                        },
                                        minutesDivider = {
                                            Text(
                                                modifier = Modifier.size(24.dp),
                                                textAlign = TextAlign.Center,
                                                text = "m"
                                            )
                                        },
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(20.dp)),
                                        dividersColor = Color.White
                                    )
//Color picker
                                    val colorDialogState = rememberMaterialDialogState()

                                    var pickedColorInt by remember { mutableStateOf(0) }
                                    MaterialDialog(dialogState = colorDialogState) {
                                        colorChooser(
                                            colors = ColorPalette.Primary,
                                            initialSelection = pickedColorInt,
                                            waitForPositiveButton = false,
                                            onColorSelected = {
                                                pickedColor = it
                                                pickedColorInt = ColorPalette.Primary.indexOf(it)
                                            }
                                        )
                                    }

                                    Row() {
                                        Button(
                                            modifier = Modifier
                                                .padding(16.dp)
                                                .align(Alignment.CenterVertically),
                                            onClick = {
                                                colorDialogState.show()
                                            }) {
                                            Text(text = "Choose pin color")
                                        }
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .align(Alignment.CenterVertically)
                                                .clip(CircleShape)
                                                .background(pickedColor)
                                        )
                                    }
//Cancel/Submit buttons
/*
LaunchedEffect(Unit, block = {
        userViewModel.getCurrentUser()
}
)*/
                                    Row(
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Button(onClick = {
                                            navController.navigate(Screen.Home.route)
                                        }) {
                                            Text(text = "Cancel")
                                        }
                                        Button(onClick = {
                                            eventViewModel.createEvent(
                                                NewEventRequestBody(
                                                    name = title,
                                                    date = pickedDate.atTime(pickedTime).toString(),
                                                    durationInSeconds = pickedDurationTime.hours * 3600 + pickedDurationTime.minutes * 60,
                                                    latitude = LocationStore.storedLatitude,
                                                    longitude = LocationStore.storedLongitude,
                                                    description = description,
                                                    color = pickedColor.toString()
                                                )
                                            )
                                            //TODO: add participants... and getting long- and latitude
                                        }) {
                                            Text(text = "Submit")
                                        }
                                    }
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
fun NewMeetingListItem(
    navController: NavController,
    text: String,
    image: Image? = null,
    onClick: () -> Unit
) {
    val imageUri = rememberSaveable { mutableStateOf("") }
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
                headlineText = { Text(text = text) },
                modifier = Modifier.clickable {
                    onClick()
                }
            )
        }
    }
    //Divider()
}