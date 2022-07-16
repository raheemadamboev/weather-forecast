package xyz.teamgravity.weatherforecast.injection.provide

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import xyz.teamgravity.weatherforecast.data.hardware.AndroidLocationTracker
import xyz.teamgravity.weatherforecast.data.remote.api.WeatherApi
import xyz.teamgravity.weatherforecast.data.remote.api.WeatherApiConst
import xyz.teamgravity.weatherforecast.data.remote.datasource.WeatherDataSource
import xyz.teamgravity.weatherforecast.data.repository.WeatherRepositoryImp
import xyz.teamgravity.weatherforecast.domain.hardware.LocationTracker
import xyz.teamgravity.weatherforecast.domain.repository.WeatherRepository
import xyz.teamgravity.weatherforecast.domain.usecase.GetLocation
import xyz.teamgravity.weatherforecast.domain.usecase.GetWeather
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun provideWeatherApi(converterFactory: Converter.Factory): WeatherApi = Retrofit.Builder()
        .baseUrl(WeatherApiConst.BASE_URL)
        .addConverterFactory(converterFactory)
        .build()
        .create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherDataSource(weatherApi: WeatherApi): WeatherDataSource = WeatherDataSource(weatherApi)

    @Provides
    @Singleton
    fun provideWeatherRepositoryImp(weatherDataSource: WeatherDataSource): WeatherRepositoryImp = WeatherRepositoryImp(weatherDataSource)

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(application: Application): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    @Provides
    @Singleton
    fun provideAndroidLocationTracker(
        application: Application,
        fusedLocationProviderClient: FusedLocationProviderClient,
    ): AndroidLocationTracker = AndroidLocationTracker(
        app = application,
        client = fusedLocationProviderClient
    )

    @Provides
    @Singleton
    fun provideGetWeather(weatherRepository: WeatherRepository): GetWeather = GetWeather(weatherRepository)

    @Provides
    @Singleton
    fun provideGetLocation(locationTracker: LocationTracker): GetLocation = GetLocation(locationTracker)

    @Provides
    @Singleton
    fun provideTimberDebugTree(): Timber.DebugTree = Timber.DebugTree()
}