package com.example.meetup

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.contentValuesOf
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Profile (navController: NavController){//, user : User) {
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

    var username by rememberSaveable{ mutableStateOf("") }
    var name by rememberSaveable {mutableStateOf("")}
    //var password by remember { mutableStateOf("") }
    val selectedItem = remember { mutableStateOf(items[0]) }

    //username = user.username
    //name = user.name
    //password = user.password


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
                                Text(text = "Profil")
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
                    Text(text = "Profil")
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
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(text = "Anuluj",
                                        modifier = Modifier.clickable { notification.value = "Anulowano zmiany" })
                                    Text(text = "Zapisz",
                                        modifier = Modifier.clickable { notification.value = "Zapisano zmiany" })
                                }

                                ProfileImage()

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 4.dp, end = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(text = "Imię", modifier = Modifier.width(100.dp))
                                    TextField(
                                        value = name,
                                        onValueChange = { name = it },
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color.Transparent,
                                            textColor = Color.Black
                                        )
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 4.dp, end = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(text = "Nazwa użytkownika", modifier = Modifier.width(100.dp))
                                    TextField(
                                        value = username,
                                        onValueChange = { username = it },
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color.Transparent,
                                            textColor = Color.Black
                                        )
                                    )
                                }
                                Button(
                                    onClick = {
                                        notification.value = "Usunięto konto"
                                    },
                                    modifier = Modifier
                                        .padding(
                                            horizontal = BUTTON_HORIZONTAL_PADDING.dp,
                                            vertical = 10.dp
                                        )
                                        .fillMaxWidth()
                                        .height(BUTTON_HEIGHT.dp)
                                ) {
                                    Text(text = "Usuń konto", fontSize = BUTTON_FONT_SIZE.sp)
                                }
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



@Composable
fun ProfileImage(){
    val imageUri = rememberSaveable{mutableStateOf("") }
    val painter = rememberImagePainter(
        if(imageUri.value.isEmpty())
            R.drawable.ic_user
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){ uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }



    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ){
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )

        }
        Text(text = "Zmień zdjęcie profilowe")


    }



}
