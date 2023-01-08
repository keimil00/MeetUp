package com.example.meetup.screen

import android.annotation.SuppressLint
import android.media.Image
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meetup.R
import com.example.meetup.component.Drawer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Popup
import com.github.skydoves.colorpicker.compose.*
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.color.ColorPalette
import com.vanpra.composematerialdialogs.color.colorChooser
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import io.ktor.server.application.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMeeting(navController: NavController) {
    val context = LocalContext.current

    Drawer(
        navController = navController,
        title = stringResource(id = R.string.new_meeting),
        content = { paddingValues ->


            var title by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            var dateTime by remember { mutableStateOf(LocalDate.now()) }
            var color by remember { mutableStateOf(Color.Blue) }
            var time by remember { mutableStateOf(LocalTime.now()) }


            val dateDialogState = rememberMaterialDialogState()
            val timeDialogState = rememberMaterialDialogState()

            var pickedTime by remember {
                mutableStateOf(LocalTime.now())
            }
            MaterialDialog (
                dialogState = timeDialogState,
                buttons = {
                    positiveButton(text = "ok")
                    negativeButton(text = "Cancel")
                }
            ){
                timepicker(
                    initialTime = LocalTime.NOON,
                    title = "Pick a date"
                ){
                    pickedTime = it;
                }
            }
            var pickedDate by remember{
                mutableStateOf(LocalDate.now())
            }
            MaterialDialog (
                dialogState = dateDialogState,
                buttons = {
                    positiveButton(text = "ok")
                    negativeButton(text = "Cancel")
                }
            ){
                datepicker(
                    initialDate = LocalDate.now(),
                    title = "Pick a date"
                ){
                    pickedDate = it;
                }
            }


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

                                    NewMeetingListItem(navController = navController, text = "Tytuł", onClick = {

                                    })

                                    NewMeetingListItem(navController = navController, text = "Osoby", onClick = {

                                    })

                                    NewMeetingListItem(navController = navController, text = "Data i godzina", onClick = {
                                        timeDialogState.show()
                                        dateDialogState.show()

                                    })

                                    //  ShowTimePicker(context = view ,initHour = 2, initMinute = 2);

                                    DateAndTimePickers(navController);

                                    NewMeetingListItem(navController = navController, text = "Opis", onClick = {

                                    })

                                    NewMeetingListItem(navController = navController, text = "Opis", onClick = {

                                    })


                                    NewMeetingListItem(navController = navController, text = "Powiadomienie", onClick = {

                                    })

                                    NewMeetingListItem(navController = navController, text = "Kolor pinezki", onClick = {

                                    })
                                    bigColorPicker();

                                    val colorDialogState = rememberMaterialDialogState()

                                    val dialogState = rememberMaterialDialogState()
                                    MaterialDialog(dialogState = colorDialogState) {

                                        colorChooser(colors = ColorPalette.Primary)

                                    }

                                    Button(onClick = {
                                        colorDialogState.show()
                                    })  {
                                        Text(text = "Wybierz kolor")
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
                headlineText = { Text(text = text) },
                leadingContent = {
                    if (image == null) {
                        R.drawable.ic_user
                    } else {
                        image
                    }
                },
                modifier = Modifier.clickable {
                    onClick()
                }
            )
        }
    }
    //Divider()
}

@Composable
fun DateAndTimePickers(navController: NavController){
    var pickedDate by remember{
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by remember {
        mutableStateOf(LocalTime.now())
    }
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

  /*  NewMeetingListItem(navController = navController, text = "Data -> " + formattedDate, onClick = {
        dateDialogState.show()
    })
    NewMeetingListItem(navController = navController, text = "Godzina -> " + formattedTime, onClick = {
        timeDialogState.show()
    })
    */
    Row(
        modifier = Modifier.fillMaxSize()//,
        // horizontalAlignment = Alignment.CenterHorizontally,
        // verticalArrangement = Arrangement.Center


    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Button(onClick = {
                dateDialogState.show()
            })  {
                Text(text = "Wybierz datę")
            }
            Text(text = formattedDate)
        }

        //  Spacer(modifier = Modifier.height(16.dp))
        //  Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Button(onClick = {
                timeDialogState.show()
            }) {
                Text(text = "Wybierz godzinę")
            }
            Text(text = formattedTime)
        }
    }

    MaterialDialog (
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "ok")
            negativeButton(text = "Cancel")
        }
    ){
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date"
        ){
            pickedDate = it;
        }
    }
    MaterialDialog (
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "ok")
            negativeButton(text = "Cancel")
        }
    ){
        timepicker(
            initialTime = LocalTime.NOON,
            title = "Pick a date"
        ){
            pickedTime = it;
        }
    }

}


@Composable
fun bigColorPicker() {
    // on below line we are creating a variable for controller
    val controller = rememberColorPickerController()
    val context = LocalContext.current


    var popupControl by remember {
        mutableStateOf(false)
    }

    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp)
    ) {
        // on below line we are adding a row.
        Row(
            // on below line we are adding a modifier
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Toast
                        .makeText(
                            context,
                            controller.selectedColor.toString(),
                            Toast.LENGTH_LONG
                        )
                        .show();
                },
            // on below line we are adding horizontal
            // and vertical alignment.
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // on below line we are adding a alpha tile.
            AlphaTile(
                // on below line we are
                // adding modifier to it
                modifier = Modifier
                    .fillMaxWidth()
                    // on below line
                    // we are adding a height.
                    .height(60.dp)
                    // on below line we are adding clip.
                    .clip(RoundedCornerShape(6.dp))
                    .clickable {
                        popupControl = true;
                    },
                // on below line we are adding controller.
                controller = controller,

                )
        }
        if(popupControl)
        {
            Popup(
                onDismissRequest = {popupControl = false}
            ) {
                Column(
                    // on below line we are adding a modifier to it,
                    modifier = Modifier
                        .fillMaxSize()
                        // on below line we are adding a padding.
                        .padding(all = 30.dp)
                ) {
                    // on below line we are
                    // adding horizontal color picker.
                    HsvColorPicker(
                        // on below line we are
                        // adding a modifier to it
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .padding(10.dp),
                        // on below line we are
                        // adding a controller
                        controller = controller,
                        // on below line we are
                        // adding on color changed.
                        onColorChanged = {}
                    )
                }
            }
        }
        /*
        // on below line we are
        // adding horizontal color picker.
        HsvColorPicker(
            // on below line we are
            // adding a modifier to it
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(10.dp),
            // on below line we are
            // adding a controller
            controller = controller,
            // on below line we are
            // adding on color changed.
            onColorChanged = {}
        )
        // on below line we are adding a alpha slider.
        AlphaSlider(
            // on below line we
            // are adding a modifier to it.
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            // on below line we are
            // adding a controller.
            controller = controller,
            // on below line we are
            // adding odd and even color.
            tileOddColor = Color.White,
            tileEvenColor = Color.Black
        )
        // on below line we are
        // adding a brightness slider.
        BrightnessSlider(
            // on below line we
            // are adding a modifier to it.
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            // on below line we are
            // adding a controller.
            controller = controller,
        )*/
    }
}