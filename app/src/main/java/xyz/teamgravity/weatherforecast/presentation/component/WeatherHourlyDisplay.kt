package xyz.teamgravity.weatherforecast.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import xyz.teamgravity.weatherforecast.R
import xyz.teamgravity.weatherforecast.domain.model.WeatherDataModel
import java.time.format.DateTimeFormatter

@Composable
fun WeatherHourlyDisplay(
    weather: WeatherDataModel,
) {
    val time = remember(weather) { weather.time.format(DateTimeFormatter.ofPattern("HH:mm")) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = time)
        Image(
            painter = painterResource(id = weather.type.icon),
            contentDescription = weather.type.name.asString()
        )
        Text(
            text = stringResource(id = R.string.your_celsius, weather.temperature),
            fontWeight = FontWeight.Bold
        )
    }
}