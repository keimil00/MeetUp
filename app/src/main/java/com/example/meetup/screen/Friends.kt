package com.example.meetup.screen

import android.annotation.SuppressLint
import android.media.Image
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.meetup.R
import com.example.meetup.model.MenuItem
import com.example.meetup.navigation.Screen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Friends (navController: NavController) {
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

    //var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val selectedItem = remember { mutableStateOf(items[0]) }

    var name by rememberSaveable {mutableStateOf("default name")}
    var username by rememberSaveable{ mutableStateOf("default username") }

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
                                Text(text = "Znajomi")
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
                    Text(text = "Znajomi")
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
                            val notification = rememberSaveable{ mutableStateOf("") }
                            if (notification.value.isNotEmpty()){
                                Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
                                notification.value = ""
                            }

                            Column(modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .padding(8.dp)
                            ){
                                FriendListItem(navController = navController, name = "Anna Nowak", username = "anka_skakanka")
                                FriendListItem(navController = navController, name = "Janek Kowalski", username = "janekk")
                                FriendListItem(navController = navController, name = "Kasia Wiśniewska", username = "wisnia11")
                                FriendListItem(navController = navController, name = "Piotr Malinowski", username = "miliniak23")
                            }
                        }
                    }
                }

                var skipHalfExpanded by remember { mutableStateOf(false) }
                val state = rememberModalBottomSheetState(
                    initialValue = ModalBottomSheetValue.HalfExpanded,
                    skipHalfExpanded = skipHalfExpanded
                )
                val scope = rememberCoroutineScope()

            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendListItem(navController: NavController, name: String, username: String, image: Image? = null){ //optionally: user : User
    val imageUri = rememberSaveable{mutableStateOf("") }
    val painter = rememberImagePainter(
        if(imageUri.value.isEmpty())
            R.drawable.ic_user
        else
            imageUri.value
    )

    Column {
        ListItem(
            headlineText = { Text(name) },
            supportingText = { Text(username) },
            leadingContent = {
                if(image == null)
                {
                    R.drawable.ic_user
                }
                else
                {
                    image
                }
            },
            modifier = Modifier.clickable {
                navController.navigate(
                    Screen.Profile.route//(user)
                ) }
        )
        Divider()
    }
}
