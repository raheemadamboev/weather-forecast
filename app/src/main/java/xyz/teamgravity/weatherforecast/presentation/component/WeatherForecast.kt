package xyz.teamgravity.weatherforecast.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.teamgravity.weatherforecast.R
import xyz.teamgravity.weatherforecast.domain.model.WeatherDataModel

@Composable
fun WeatherForecast(
    data: List<WeatherDataModel>,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.today),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow {
            items(data) { weather ->
                WeatherHourlyDisplay(weather = weather)
            }
        }
    }
}