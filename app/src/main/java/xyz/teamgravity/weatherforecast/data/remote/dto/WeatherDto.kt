package xyz.teamgravity.weatherforecast.data.remote.dto

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly") val data: WeatherDataDto,
)