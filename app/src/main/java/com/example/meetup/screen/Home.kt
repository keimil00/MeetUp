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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meetup.R
import com.example.meetup.component.Drawer
import com.example.meetup.component.MapView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun Home (navController: NavController) {
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
                        // TODO: Add event
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
                LazyColumn {
                    items(10) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .clickable {
                                  // TODO : Navigate to event details
                                },
                            shape = MaterialTheme.shapes.medium,
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            content = {
                                // Here are used string literals because they will be replaced with real data
                                Text(text = "Jan Kowalski",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(10.dp),
                                    textAlign = TextAlign.Left,)
                                Text(text = "Idziemy na spacer",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(10.dp),
                                    textAlign = TextAlign.Left,)
                            }
                        )
                    }
                }
            },
            sheetPeekHeight = 160.dp
        ) {
            // Content behind BottomSheet
            MapView(Modifier.padding(paddingValues))

        }
    })
}


@ExperimentalMaterialApi
@Composable
fun BottomSheetScaffoldSample() {
    val scope = rememberCoroutineScope()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            // BottomSheet content
            Divider(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 160.dp),
                thickness = 3.dp,
                color = MaterialTheme.colorScheme.inverseSurface
            )
            LazyColumn {
                items(10) {
                    ListItem(
                        text = { Text("Item $it") },
                        icon = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        },
        sheetPeekHeight = 56.dp
    ) {
        // Content behind BottomSheet
        MapView()

    }
}
