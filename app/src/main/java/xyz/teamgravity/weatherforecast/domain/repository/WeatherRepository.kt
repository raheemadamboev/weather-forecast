package xyz.teamgravity.weatherforecast.domain.repository

import kotlinx.coroutines.flow.Flow
import xyz.teamgravity.weatherforecast.core.util.Resource
import xyz.teamgravity.weatherforecast.domain.model.WeatherModel

interface WeatherRepository {

    ///////////////////////////////////////////////////////////////////////////
    // Get
    ///////////////////////////////////////////////////////////////////////////

    suspend fun getWeather(latitude: Double, longitude: Double): Flow<Resource<List<List<WeatherModel>>>>
}