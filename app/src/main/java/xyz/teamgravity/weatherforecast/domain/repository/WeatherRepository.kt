package xyz.teamgravity.weatherforecast.domain.repository

import kotlinx.coroutines.flow.Flow
import xyz.teamgravity.weatherforecast.core.util.Resource
import xyz.teamgravity.weatherforecast.domain.model.WeatherDataModel
import xyz.teamgravity.weatherforecast.domain.model.WeatherModel

interface WeatherRepository {

    ///////////////////////////////////////////////////////////////////////////
    // GET
    ///////////////////////////////////////////////////////////////////////////

    fun getWeather(latitude: Double, longitude: Double): Flow<Resource<WeatherModel>>
}