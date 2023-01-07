package com.example.meetup.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import org.osmdroid.api.IMapController
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.CopyrightOverlay
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context)
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

    // rotation gestures and zooming
    val rotationGestureOverlay = RotationGestureOverlay(mapView)
    rotationGestureOverlay.isEnabled
    mapView.setMultiTouchControls(true)
    mapView.overlays.add(rotationGestureOverlay)

    // copyright to OSM
    val copyrightOverlay = CopyrightOverlay(context)
    mapView.overlays.add(copyrightOverlay)

    val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), mapView)
    locationOverlay.enableMyLocation()
    mapView.overlays.add(locationOverlay)

    // if no location, default starting place in Wroclaw
    val mapController: IMapController = mapView.controller
    mapController.setZoom(10.5)
    val startPoint = GeoPoint(51.110150, 17.031780)
    mapController.setCenter(startPoint)


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