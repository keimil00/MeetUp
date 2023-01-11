package com.example.meetup.location

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import com.example.meetup.R
import com.vanpra.composematerialdialogs.color.ColorPalette
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


class MarkerManager {
    companion object {
        private fun prepareDrawable(context: Context, colorString: String): Drawable? {
            val image = ContextCompat.getDrawable(context, R.drawable.custom_marker)

            val colorARGB: Int = ColorPalette.Primary.get(colorString.toInt()).toArgb()

            if (image != null) {
                image.setColorFilter(colorARGB, PorterDuff.Mode.SRC_ATOP)
            }
            return image
        }

        fun createMarker(
            mapView: MapView,
            latitude: Double,
            longitude: Double,
            name: String,
            description: String,
            color: String,
            context: Context
        ): Marker {
            val marker = Marker(mapView)
            marker.position = GeoPoint(latitude, longitude)
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            //marker.setIcon(context.resources.getDrawable(R.drawable.meetup_marker))
            marker.icon = prepareDrawable(context, color)
            //marker.setInfoWindow()
            marker.title = name
            marker.snippet = description
            return marker
        }
    }
}