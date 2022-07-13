package xyz.teamgravity.weatherforecast.domain.usecase

import android.location.Location
import xyz.teamgravity.weatherforecast.core.util.Resource
import xyz.teamgravity.weatherforecast.domain.hardware.LocationTracker

class GetLocation(
    private val location: LocationTracker,
) {

    suspend operator fun invoke(): Resource<Location> {
        return location.getCurrentLocation()
    }
}