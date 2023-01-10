package com.example.meetup.screen

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meetup.R
import com.example.meetup.component.Drawer
import com.example.meetup.component.MapView
import com.example.meetup.location.LocationStore
import com.example.meetup.model.Event
import com.example.meetup.navigation.Screen
import com.example.meetup.view_model.EventViewModel
import com.example.meetup.view_model.PermissionTestViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun Home (navController: NavController, eventViewModel: EventViewModel = hiltViewModel()) {
    val context = LocalContext.current

    // create a store for user location
    LaunchedEffect(Unit, block = {
        LocationStore.createStore(context)
        LocationStore.refreshLocation(context)
    })

    Drawer(navController = navController, title = stringResource(id = R.string.map), content = { paddingValues ->
        val scope = rememberCoroutineScope()

        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
        )

        val floatingActionButton = FloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }

        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetShape = MaterialTheme.shapes.medium,
            sheetElevation = BottomSheetScaffoldDefaults.SheetElevation,
            sheetBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            sheetContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.absoluteOffset(x = 0.dp, y = (-100).dp),
                    onClick = {
                        navController.navigate(
                            Screen.NewMeeting.route
                        )
                    },
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ) {
                    Icon(
                        Icons.Default.AddLocation,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(30.dp)
                    )
                }

                FloatingActionButton(
                    modifier = Modifier.absoluteOffset(x = 0.dp, y = (-35).dp),
                    onClick = {
                        // TODO: Add event
                    },
                    shape = MaterialTheme.shapes.medium,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ) {
                    Icon(
                        Icons.Default.NearMe,
                        contentDescription = "Localized description"
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            sheetContent = {
                // BottomSheet content
                Divider(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 160.dp),
                    thickness = 3.dp,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
                Text(text = stringResource(id = R.string.events),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,)

                eventViewModel.getEventsList(LocationStore.storedLatitude, LocationStore.storedLongitude)


                LazyColumn {
                    eventViewModel.eventsList.forEach { event ->
                        item {
                            EventCard(event, navController)
                        }
                    }
                }

            },
            sheetPeekHeight = 160.dp
        ) {
            // Content behind BottomSheet
            MapView(Modifier.padding(paddingValues), rememberScaffoldState(),PermissionTestViewModel(),eventViewModel
            )

        }
    })
}

@Composable
fun EventCard(event: Event, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                navController.navigate(
                    Screen.EventDetails.withArgs(event.id.toString())   // TODO param
                )
            },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        content = {
            Text(text = event.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Left,)
            Text(text = event.ownerId.toString(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Left,)
        }
    )
}
