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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.meetup.R
import com.example.meetup.component.Drawer
import com.example.meetup.model.MenuItem
import com.example.meetup.navigation.Screen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Friends (navController: NavController) {
    Drawer(navController = navController, title = stringResource(id = R.string.friends), content = {
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
    })
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
