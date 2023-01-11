package com.example.meetup.component

import android.Manifest
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.meetup.BuildConfig
import com.madhu.locationpermission.permission.PermissionUI
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView
import com.example.meetup.view_model.PermissionTestViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.meetup.location.permission.PermissionAction
import androidx.compose.runtime.*
import com.example.meetup.R
import com.example.meetup.view_model.EventViewModel

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    permissionTestViewModel: PermissionTestViewModel,
    eventViewModel: EventViewModel,
    onLoad: ((map: MapView) -> Unit)? = null
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val performLocationAction by permissionTestViewModel.performLocationAction.collectAsState()



    if (performLocationAction) {

        PermissionUI(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
            stringResource(id = R.string.permission_location_rationale),
            scaffoldState
        ) { permissionAction ->
            when (permissionAction) {
                is PermissionAction.OnPermissionGranted -> {
                    permissionTestViewModel.setPerformLocationAction(false)
                    //Todo: do something now as we have location permission
                }
                is PermissionAction.OnPermissionDenied -> {
                    permissionTestViewModel.setPerformLocationAction(false)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        permissionTestViewModel.setPerformLocationAction(true)
    }

    Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID   // TODO do on creation

    val mapViewState = rememberMapViewWithLifecycle(eventViewModel)

    AndroidView(
        { mapViewState },
        modifier
    ) { mapView -> onLoad?.invoke(mapView) }

}