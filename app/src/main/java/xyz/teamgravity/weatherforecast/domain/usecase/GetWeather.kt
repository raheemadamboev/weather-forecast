package xyz.teamgravity.weatherforecast.domain.usecase

import kotlinx.coroutines.flow.Flow
import xyz.teamgravity.weatherforecast.core.util.Resource
import xyz.teamgravity.weatherforecast.domain.model.WeatherModel
import xyz.teamgravity.weatherforecast.domain.repository.WeatherRepository

class GetWeather(
    private val repository: WeatherRepository,
) {

    operator fun invoke(latitude: Double, longitude: Double): Flow<Resource<WeatherModel>> {
        return repository.getWeather(latitude = latitude, longitude = longitude)
    }
}