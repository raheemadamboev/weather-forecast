package xyz.teamgravity.weatherforecast.data.mapper

import xyz.teamgravity.weatherforecast.core.util.WeatherType
import xyz.teamgravity.weatherforecast.data.remote.dto.WeatherDataDto
import xyz.teamgravity.weatherforecast.data.remote.dto.WeatherDto
import xyz.teamgravity.weatherforecast.domain.model.WeatherDataModel
import xyz.teamgravity.weatherforecast.domain.model.WeatherModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.sortedWithDaysAndHours(): List<List<WeatherDataModel>> {
    return time.mapIndexed { index, time ->
        IndexedWeatherData(
            index = index,
            data = WeatherDataModel(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                type = WeatherType.fromWeatherCode(weatherCodes[index].toInt()),
                temperature = temperatures[index],
                pressure = pressures[index],
                windSpeed = windSpeeds[index],
                humidity = humidity[index]
            )
        )
    }.groupBy { data ->
        data.index / 24
    }.mapValues { entry ->
        entry.value.map { it.data }
    }.values.toList()
}

fun WeatherDto.toWeatherModel(): WeatherModel {
    val weatherData = data.sortedWithDaysAndHours()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherData.getOrNull(0)?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        return@find it.time.hour == hour
    }
    return WeatherModel(
        weatherDataPerDay = weatherData,
        currentWeatherData = currentWeatherData
    )
}

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherDataModel,
)
