package com.example.meetup.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.meetup.location.LocationStore
import com.example.meetup.location.MarkerManager
import org.osmdroid.api.IMapController
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.CopyrightOverlay
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import com.example.meetup.view_model.EventViewModel

object RynekCoords {
    const val RYNEK_LAT = 51.110150
    const val RYNEK_LON = 17.031780
}


@Composable
fun rememberMapViewWithLifecycle(eventViewModel: EventViewModel = hiltViewModel()): MapView {
    // Getting events
    LaunchedEffect(Unit, block = {
        eventViewModel.getEventsList(RynekCoords.RYNEK_LAT, RynekCoords.RYNEK_LON)      // TODO ?
    })

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

    // copyright to OSM
    val copyrightOverlay = CopyrightOverlay(context)
    mapView.overlays.add(copyrightOverlay)

    val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), mapView)
    locationOverlay.enableMyLocation()
    mapView.overlays.add(locationOverlay)

    // if no location, default starting place in Wroclaw
    val mapController: IMapController = mapView.controller
    mapController.setZoom(10.5)
    val startPoint = GeoPoint(LocationStore.storedLatitude, LocationStore.storedLongitude)
    mapController.setCenter(startPoint)

    // one example marker
//    val marker1 = Marker(mapView)
//    marker1.position = GeoPoint(RynekCoords.RYNEK_LAT, RynekCoords.RYNEK_LON)
//    marker1.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
//    //marker.setIcon(context.resources.getDrawable(R.drawable.ic_gps_location))
//    marker1.setInfoWindow(null)
//    mapView.overlays.add(marker1)


    // overlay markers from api
    for (event in eventViewModel.eventsList) {
        val createdMarker = MarkerManager.createMarker(mapView, event.latitude, event.longitude, event.name, event.description)
        mapView.overlays.add(createdMarker)
    }

    // rotation gestures and zooming
    val rotationGestureOverlay = RotationGestureOverlay(mapView)
    rotationGestureOverlay.isEnabled
    mapView.setMultiTouchControls(true)
    mapView.overlays.add(rotationGestureOverlay)

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