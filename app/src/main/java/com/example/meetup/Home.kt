package com.example.meetup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home (navController: NavController) {
    Drawer(navController = navController, content = { paddingValues ->
        MapView(Modifier.padding(paddingValues))
        var skipHalfExpanded by remember { mutableStateOf(false) }
        val state = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.HalfExpanded,
            skipHalfExpanded = skipHalfExpanded
        )
        val scope = rememberCoroutineScope()
        ModalBottomSheetLayout(
            sheetState = state,
            sheetShape = MaterialTheme.shapes.large,
            sheetElevation = ModalBottomSheetDefaults.Elevation,
            sheetBackgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            sheetContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            scrimColor = ModalBottomSheetDefaults.scrimColor,
            sheetContent = {
                Divider(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 160.dp),
                    thickness = 3.dp,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
                LazyColumn {
                    items(12) {
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
            }
        ) {

        }
    })
}

