package com.example.meetup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView


@Composable
fun MapView(
    modifier: Modifier = Modifier,
    onLoad: ((map: MapView) -> Unit)? = null
) {
    Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID   // TODO do on creation

    val mapViewState = rememberMapViewWithLifecycle()

    AndroidView(
        { mapViewState },
        modifier
    ) { mapView -> onLoad?.invoke(mapView) }

}