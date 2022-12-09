package com.example.meetup.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.meetup.R
import com.example.meetup.navigation.Screen

const val BUTTON_HEIGHT = 60
const val BUTTON_HORIZONTAL_PADDING = 30
const val BUTTON_FONT_SIZE = 20
const val TEXT_FONT_SIZE = 20



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login (navController: NavController)
{
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

    }
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = stringResource(id = R.string.background_image_description),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Card(
            modifier = Modifier
                .padding(top = 130.dp)
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                TextField(
                    value = username,
                    onValueChange = { newText -> username = newText },
                    label = { Text(text = stringResource(id = R.string.username), fontSize = TEXT_FONT_SIZE.sp) },
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enter_username_msg),
                            fontSize = TEXT_FONT_SIZE.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    textStyle = TextStyle(fontSize = TEXT_FONT_SIZE.sp),
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(id = R.string.password), fontSize = TEXT_FONT_SIZE.sp) },
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enter_password_msg),
                            fontSize = TEXT_FONT_SIZE.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    textStyle = TextStyle(fontSize = TEXT_FONT_SIZE.sp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(
                            Screen.Home.route
                        )
                    },
                    modifier = Modifier
                        .padding(
                            horizontal = BUTTON_HORIZONTAL_PADDING.dp,
                            vertical = 10.dp
                        )
                        .fillMaxWidth()
                        .height(BUTTON_HEIGHT.dp)
                ) {
                    Text(text = stringResource(id = R.string.sign_in), fontSize = BUTTON_FONT_SIZE.sp)
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.or), fontSize = TEXT_FONT_SIZE.sp)
                }
                Button(
                    onClick = {
                        navController.navigate(Screen.ActivityRegister.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier
                        .padding(
                            horizontal = BUTTON_HORIZONTAL_PADDING.dp,
                            vertical = 10.dp
                        )
                        .fillMaxWidth()
                        .height(BUTTON_HEIGHT.dp)
                ) {
                    Text(text = stringResource(id = R.string.sign_up), fontSize = BUTTON_FONT_SIZE.sp)
                }
            }
        }
    }
}