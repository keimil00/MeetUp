package com.example.meetup.screen

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.meetup.component.Drawer
import com.example.meetup.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile (navController: NavController){//, user : User) {
    Drawer(navController = navController, content = {
        var username by rememberSaveable{ mutableStateOf("") }
        var name by rememberSaveable {mutableStateOf("")}
        //var password by remember { mutableStateOf("") }
        //username = user.username
        //name = user.name
        //password = user.password
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
    })
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
            .fillMaxSize(),
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
                    .wrapContentSize(align = Alignment.Center)
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )

        }
        Text(text = "Zmień zdjęcie profilowe")
    }
}
