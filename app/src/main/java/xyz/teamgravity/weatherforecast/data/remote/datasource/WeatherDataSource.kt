package xyz.teamgravity.weatherforecast.data.remote.datasource

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import xyz.teamgravity.weatherforecast.R
import xyz.teamgravity.weatherforecast.core.util.Resource
import xyz.teamgravity.weatherforecast.core.util.UniversalText
import xyz.teamgravity.weatherforecast.data.remote.api.WeatherApi
import xyz.teamgravity.weatherforecast.data.remote.dto.WeatherDto

class WeatherDataSource(
    private val api: WeatherApi,
) {

    ///////////////////////////////////////////////////////////////////////////
    // Get
    ///////////////////////////////////////////////////////////////////////////

    suspend fun getWeather(latitude: Double, longitude: Double): Flow<Resource<WeatherDto>> {
        return flow {
            emit(Resource.Loading())
            try {
                val weather = api.getWeather(latitude = latitude, longitude = longitude)
                emit(Resource.Success(weather))
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                val error = if (e.message == null) UniversalText.Resource(id = R.string.error_unknown)
                else UniversalText.Dynamic(e.message!!)
                emit(Resource.Error(error))
            }
        }
    }
}