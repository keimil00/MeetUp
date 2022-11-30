package com.example.meetup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.CopyrightOverlay
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = R.id.map
        }
    }

    // Makes MapView follow the lifecycle of this composable
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    // make font size readable
    mapView.isTilesScaledToDpi = true

    // rotation gestures
    val rotationGestureOverlay = RotationGestureOverlay(mapView)
    rotationGestureOverlay.isEnabled
    mapView.setMultiTouchControls(true)
    mapView.overlays.add(rotationGestureOverlay)

    // copyright to OSM
    val copyrightOverlay = CopyrightOverlay(context)
    mapView.overlays.add(copyrightOverlay)

    // TODO fix my location
    val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), mapView)
    locationOverlay.enableMyLocation()
    mapView.overlays.add(locationOverlay)

    return mapView
}

@Composable
fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                else -> {}
            }
        }
    }