package com.example.meetup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Home (navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
// icons to mimic drawer destinations
    val items = listOf(
        MenuItem(title = "Ekran główny", Icons.Filled.Home),
        MenuItem(title = "Mój profil", Icons.Filled.ManageAccounts),
        MenuItem(title = "Czaty", Icons.Filled.Chat),
        MenuItem(title = "Znajomi", Icons.Filled.Person),
        MenuItem(title = "O aplikacji", Icons.Filled.Info),
        MenuItem(title = "Wyloguj", Icons.Filled.Logout)
        )

    val selectedItem = remember { mutableStateOf(items[0]) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(Modifier.height(12.dp))
                    items.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = null) },
                            label = { Text(item.title) },
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = item
                                if (item.title.equals("Mój profil"))
                                    navController.navigate(Screen.Profile.route)
                                else if (item.title.equals("Znajomi"))
                                    navController.navigate(Screen.Friends.route)
                                else
                                    navController.navigate(Screen.Login.route)
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                        Spacer(Modifier.height(12.dp))
                    }
                }
            },
            content = {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(text = "Mapa")
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            navigationIcon = {
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu"
                                    )
                                }
                            }
                        )
                    }
                ) {
                    Text(text = "Home")
                }

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
            }
        )
    }
}
