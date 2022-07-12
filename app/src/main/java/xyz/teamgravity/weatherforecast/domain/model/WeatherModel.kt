package xyz.teamgravity.weatherforecast.domain.model

import xyz.teamgravity.weatherforecast.core.util.WeatherType
import java.time.LocalDateTime

data class WeatherModel(
    val time: LocalDateTime,
    val type: WeatherType,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
)