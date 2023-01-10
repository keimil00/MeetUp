package com.example.meetup.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.meetup.R
import com.example.meetup.component.Drawer
import com.example.meetup.model.Event

@Composable
fun EventDetails(navController: NavController, eventName: String?) {
    Drawer(navController = navController, title = stringResource(id = R.string.profile)) {
        Box(modifier = Modifier.fillMaxSize())
        {
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
                        if (eventName != null) {
                            Text(text = eventName, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 40.sp)
                        }
                        else {
                            Text(text = "Example event", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), fontSize = 40.sp)
                        }
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = eventName.toString(), textAlign = TextAlign.Center,  modifier = Modifier.fillMaxWidth())
                    }
                    Spacer(modifier = Modifier.size(10.dp))
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
                }
            }
        }
    }
}