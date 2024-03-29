package com.example.meetup.screen

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.meetup.R
import com.example.meetup.component.Drawer
import com.example.meetup.navigation.Screen
import com.example.meetup.ui_utils.LockScreenOrientation
import com.example.meetup.view_model.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile(
    navController: NavController,
    friendUsername: String? = null,
    friendFirstName: String? = null,
    friendSurname: String? = null,
    friendIconId: String? = null,
    userViewModel: UserViewModel = hiltViewModel()
) {//, user : User) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    LaunchedEffect(Unit, block = {
        userViewModel.getCurrentUser()
    })
    Drawer(navController = navController, title = stringResource(id = R.string.profile)) {
        var username = userViewModel.currentUser.username
        var firstName = userViewModel.currentUser.firstName
        var lastName = userViewModel.currentUser.lastName
        if (friendUsername != null) {
            username = friendUsername
            firstName = friendFirstName!!
            lastName = friendSurname!!
        }

        Text(text = "Profil")
        Box(modifier = Modifier.fillMaxSize())
        {
            Card(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 20.dp, vertical = 40.dp)
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
                    ProfileImage(friendIconId, userViewModel)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$firstName $lastName",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 40.sp
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = username,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Button(
                        onClick = {
                            navController.navigate(Screen.Friends.route)
                        },
                        modifier = Modifier
                            .padding(
                                horizontal = BUTTON_HORIZONTAL_PADDING.dp,
                                vertical = 10.dp
                            )
                            .fillMaxWidth()
                            .height(BUTTON_HEIGHT.dp)
                    ) {
                        Text(text = "See friends", fontSize = BUTTON_FONT_SIZE.sp)
                    }
                }
            }
        }
    }
}


@Composable
fun ProfileImage(id: String? = null, userViewModel: UserViewModel = hiltViewModel()) {
    LaunchedEffect(Unit, block = {
        userViewModel.getCurrentUser()
    })
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = rememberAsyncImagePainter(
        if (imageUri.value.isEmpty())
            R.drawable.ic_user
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(200.dp)
        ) {
            if (id != null) {
                val context = LocalContext.current
                androidx.compose.material3.Icon(
                    painter = painterResource(
                        id = context.resources.getIdentifier(
                            "images${
                                id.toInt().mod(71)
                            }", "drawable", context.packageName
                        )
                    ),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(200.dp)//.background(color = Color.Black)
                )
            } else {
                val context = LocalContext.current
                androidx.compose.material3.Icon(
                    painter = painterResource(
                        id = context.resources.getIdentifier(
                            "images${
                                userViewModel.currentUser.id.mod(
                                    71
                                ) + 2
                            }", "drawable", context.packageName
                        )
                    ),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(200.dp)//.background(color = Color.Black)
                )
                /*
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                        .clickable { launcher.launch("image\*") },
                    //contentScale = ContentScale.Crop
                )*/
            }
        }
    }
}
