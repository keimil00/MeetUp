package com.example.meetup


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meetup.ui.theme.MeetUpTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var username: String = "";
        var password: String = "";
        super.onCreate(savedInstanceState)
        setContent {
            MeetUpTheme {
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
                                            value = username,
                                            onValueChange = { username = it },
                                            label = { Text("Username") },
                                            singleLine = true,
                                            placeholder = { Text("Enter your username") },
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))

                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                    Column(
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        TextField(
                                            value = password,
                                            onValueChange = { password = it },
                                            label = { Text("Password") },
                                            singleLine = true,
                                            placeholder = { Text("Enter your password") },
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Button(
                                            onClick = { /*TODO*/ },
                                            contentPadding = ButtonDefaults.TextButtonContentPadding,
                                        ) {
                                            Text(text = "Login")
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
