package xyz.teamgravity.weatherforecast.domain.hardware

import android.location.Location
import xyz.teamgravity.weatherforecast.core.util.Resource

interface LocationTracker {
    suspend fun getCurrentLocation(): Resource<Location>
}