package xyz.teamgravity.weatherforecast.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import xyz.teamgravity.weatherforecast.data.remote.dto.WeatherDto

interface WeatherApi {

    ///////////////////////////////////////////////////////////////////////////
    // Get
    ///////////////////////////////////////////////////////////////////////////

    @GET("/v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherDto
}