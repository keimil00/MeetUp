package com.example.meetup.screen


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meetup.event.AuthUiEvent
import com.example.meetup.view_model.MainViewModel
import com.example.meetup.R
import com.example.meetup.authorization.AuthResult
import com.example.meetup.navigation.Screen

const val REGISTRATION_TEXT_FONT_SIZE = 15

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavController, viewModel: MainViewModel = hiltViewModel()) {
    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when(result) {
                is AuthResult.Authorized -> {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Register.route) {
                            inclusive = true
                        }
                    }
                }
                is AuthResult.Unauthorized -> {

                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
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
                .padding(top = 90.dp)
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
                    value = viewModel.state.firstName,
                    onValueChange = { viewModel.onEvent(AuthUiEvent.FirstNameChanged(it)) },
                    label = { Text(text = "Name", fontSize =REGISTRATION_TEXT_FONT_SIZE.sp) },
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Enter your name",
                            fontSize =REGISTRATION_TEXT_FONT_SIZE.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    textStyle = TextStyle(fontSize =REGISTRATION_TEXT_FONT_SIZE.sp),
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                TextField(
                    value = viewModel.state.lastName,
                    onValueChange = { viewModel.onEvent(AuthUiEvent.LastNameChanged(it)) },
                    label = { Text(text = "Surname", fontSize =REGISTRATION_TEXT_FONT_SIZE.sp) },
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Enter your name",
                            fontSize =REGISTRATION_TEXT_FONT_SIZE.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    textStyle = TextStyle(fontSize =REGISTRATION_TEXT_FONT_SIZE.sp),
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                TextField(
                    value = viewModel.state.email,
                    onValueChange = { viewModel.onEvent(AuthUiEvent.EmailChanged(it)) },
                    label = { Text(text = "Email", fontSize =REGISTRATION_TEXT_FONT_SIZE.sp) },
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Enter your Email",
                            fontSize =REGISTRATION_TEXT_FONT_SIZE.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    textStyle = TextStyle(fontSize =REGISTRATION_TEXT_FONT_SIZE.sp),
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                TextField(
                    value = viewModel.state.password,
                    onValueChange = { viewModel.onEvent(AuthUiEvent.PasswordChanged(it)) },
                    label = { Text(text = "Password", fontSize =REGISTRATION_TEXT_FONT_SIZE.sp) },
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Enter your Password",
                            fontSize =REGISTRATION_TEXT_FONT_SIZE.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    textStyle = TextStyle(fontSize =REGISTRATION_TEXT_FONT_SIZE.sp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(AuthUiEvent.SignUp)
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
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.or), fontSize =REGISTRATION_TEXT_FONT_SIZE.sp)
                }
                Button(
                    onClick = {
                        navController.navigate(Screen.Login.route){
                            popUpTo(Screen.Register.route){
                                inclusive = true
                            }
                        }
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
            }
        }
    }
}


