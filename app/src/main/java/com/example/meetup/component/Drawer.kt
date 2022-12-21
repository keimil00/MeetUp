package com.example.meetup.component

 import android.annotation.SuppressLint
 import androidx.compose.foundation.layout.*
 import androidx.compose.material.*
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.filled.*
 import androidx.compose.material3.*
 import androidx.compose.material3.DrawerValue
 import androidx.compose.material3.Icon
 import androidx.compose.material3.Text
 import androidx.compose.runtime.*
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.platform.LocalContext
 import androidx.compose.ui.unit.dp
 import androidx.navigation.NavController
 import com.example.meetup.model.MenuItem
 import com.example.meetup.navigation.Screen
 import io.ktor.http.*
 import kotlinx.coroutines.launch

/**
 * @param navController The [NavController] to use for navigation.
 * @param content The content to display in the [Drawer].
 */


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(navController: NavController, title: String, content: @Composable (PaddingValues) -> Unit) {
 val drawerState = rememberDrawerState(DrawerValue.Closed)
 val context = LocalContext.current
 val scope = rememberCoroutineScope()
// icons to mimic drawer destinations
 val items = listOf(
  MenuItem(title = "Ekran główny", Icons.Filled.Home, Screen.Home.route),
  MenuItem(title = "Mój profil", Icons.Filled.ManageAccounts, Screen.Profile.route),
  MenuItem(title = "Czaty", Icons.Filled.Chat, Screen.Home.route),
  MenuItem(title = "Znajomi", Icons.Filled.Person, Screen.Friends.route),
  MenuItem(title = "O aplikacji", Icons.Filled.Info, Screen.Home.route),
  MenuItem(title = "Wyloguj", Icons.Filled.Logout, Screen.Login.route)
 )

 val selectedItem = remember { mutableStateOf(items[0]) }
 androidx.compose.material3.Surface(
  modifier = Modifier.fillMaxSize(),
  color = androidx.compose.material3.MaterialTheme.colorScheme.background
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
        if (selectedItem.value.navRoute == Screen.Login.route){
          context.getSharedPreferences("prefs", 0)
           .edit()
           .remove("jwt")
           .apply()
        }
        navController.navigate(selectedItem.value.navRoute)
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
        Text(text = title)
       },
       colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant,
        titleContentColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
       ),
       navigationIcon = {
        androidx.compose.material3.IconButton(onClick = { scope.launch { drawerState.open() } }) {
         Icon(
          imageVector = Icons.Default.Menu,
          contentDescription = "Menu"
         )
        }
       }
      )
     }
    ) {
     content(it)
    }
   }
  )
 }
}