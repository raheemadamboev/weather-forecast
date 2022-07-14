package xyz.teamgravity.weatherforecast.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.teamgravity.weatherforecast.R
import xyz.teamgravity.weatherforecast.domain.model.WeatherDataModel
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    weather: WeatherDataModel,
) {
    Card(modifier = Modifier.padding(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.your_today, weather.time.format(DateTimeFormatter.ofPattern("HH:mm"))),
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = weather.type.icon),
                contentDescription = stringResource(id = R.string.cd_weather_today_type),
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.your_celsius, weather.temperature.toString()),
                fontSize = 50.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherDataDisplay(
                    icon = R.drawable.ic_pressure,
                    value = weather.pressure.roundToInt(),
                    unit = stringResource(id = R.string.hpa)
                )
                WeatherDataDisplay(
                    icon = R.drawable.ic_drop,
                    value = weather.humidity.roundToInt(),
                    unit = stringResource(id = R.string.percent)
                )
                WeatherDataDisplay(
                    icon = R.drawable.ic_wind,
                    value = weather.windSpeed.roundToInt(),
                    unit = stringResource(id = R.string.km_hour)
                )
            }
        }
    }
}