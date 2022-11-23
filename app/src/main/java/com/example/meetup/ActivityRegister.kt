package com.example.meetup


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meetup.ui.theme.MeetUpTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityRegister(navController: NavController){
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) };
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) };
    var emailAddress by remember { mutableStateOf(TextFieldValue("")) };
    var name by remember { mutableStateOf(TextFieldValue("")) };
    var surname by remember { mutableStateOf(TextFieldValue("")) };
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Material 3")
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        ) { values ->
            LazyColumn(contentPadding = values) {
                items(1) {
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,

                            ),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            TextField(
                                value = name,
                                onValueChange = { name = it },
                                label = { Text("Name") },
                                singleLine = true,
                                placeholder = { Text("Enter your name") },
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = surname,
                                onValueChange = { surname = it },
                                label = { Text("Surname") },
                                singleLine = true,
                                placeholder = { Text("Enter your surname") },
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = username,
                                onValueChange = { username = it },
                                label = { Text("Username") },
                                singleLine = true,
                                placeholder = { Text("Enter your username") },
                            )
                            Spacer(modifier = Modifier.height(8.dp))


                            TextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text("Password") },
                                singleLine = true,
                                placeholder = { Text("Enter your password") },
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = phoneNumber,
                                onValueChange = { phoneNumber = it },
                                label = { Text("Phone number") },
                                singleLine = true,
                                placeholder = { Text("Enter your phone number") },
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = emailAddress,
                                onValueChange = { emailAddress = it },
                                label = { Text("Email address") },
                                singleLine = true,
                                placeholder = { Text("Enter your address") },
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { navController.navigate(Screen.MainScreen.withArgs(username.text)) },
                                contentPadding = ButtonDefaults.TextButtonContentPadding,
                            ) {
                                Text(text = "Register")
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

//@ExperimentalMaterial3Api
//class ActivityRegister : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        var username: String = "";
//        var password: String = "";
//        var phoneNumber: String = "";
//        var emailAddress: String = "";
//        var name: String = "";
//        var surname: String = "";
//        super.onCreate(savedInstanceState)
//        setContent {
//            MeetUpTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Scaffold(
//                        topBar = {
//                            TopAppBar(
//                                title = {
//                                    Text(text = "Material 3")
//                                },
//                                colors = TopAppBarDefaults.smallTopAppBarColors(
//                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
//                                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
//                                )
//                            )
//                        }
//                    ) { values ->
//                        LazyColumn(contentPadding = values) {
//                            items(1) {
//                                Card(
//                                    modifier = Modifier
//                                        .padding(16.dp)
//                                        .fillMaxWidth(),
//                                    colors = CardDefaults.cardColors(
//                                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
//
//                                        ),
//                                    shape = MaterialTheme.shapes.large
//                                ) {
//                                    Column(
//                                        modifier = Modifier.padding(16.dp)
//                                    ) {
//                                        TextField(
//                                            value = name,
//                                            onValueChange = { name = it },
//                                            label = { Text("Name") },
//                                            singleLine = true,
//                                            placeholder = { Text("Enter your name") },
//                                        )
//                                        Spacer(modifier = Modifier.height(8.dp))
//                                        TextField(
//                                            value = surname,
//                                            onValueChange = { surname = it },
//                                            label = { Text("Surname") },
//                                            singleLine = true,
//                                            placeholder = { Text("Enter your surname") },
//                                        )
//                                        Spacer(modifier = Modifier.height(8.dp))
//                                        TextField(
//                                            value = username,
//                                            onValueChange = { username = it },
//                                            label = { Text("Username") },
//                                            singleLine = true,
//                                            placeholder = { Text("Enter your username") },
//                                        )
//                                        Spacer(modifier = Modifier.height(8.dp))
//
//
//                                        TextField(
//                                            value = password,
//                                            onValueChange = { password = it },
//                                            label = { Text("Password") },
//                                            singleLine = true,
//                                            placeholder = { Text("Enter your password") },
//                                        )
//                                        Spacer(modifier = Modifier.height(8.dp))
//                                        TextField(
//                                            value = phoneNumber,
//                                            onValueChange = { phoneNumber = it },
//                                            label = { Text("Phone number") },
//                                            singleLine = true,
//                                            placeholder = { Text("Enter your phone number") },
//                                        )
//                                        Spacer(modifier = Modifier.height(8.dp))
//                                        TextField(
//                                            value = emailAddress,
//                                            onValueChange = { emailAddress = it },
//                                            label = { Text("Email address") },
//                                            singleLine = true,
//                                            placeholder = { Text("Enter your address") },
//                                        )
//                                        Spacer(modifier = Modifier.height(8.dp))
//                                        Spacer(modifier = Modifier.height(8.dp))
//                                        Button(
//                                            onClick = { /*TODO*/ },
//                                            contentPadding = ButtonDefaults.TextButtonContentPadding,
//                                        ) {
//                                            Text(text = "Register")
//                                        }
//                                        Spacer(modifier = Modifier.height(8.dp))
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

