package com.li.weatherapp.service

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.JobIntentService
import com.li.weatherapp.R
import com.li.weatherapp.data.model.CurrentWeather
import com.li.weatherapp.data.repository.CurrentWeatherRepository
import com.li.weatherapp.data.source.remote.CurrentWeatherRemoteDataSource
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import com.li.weatherapp.widget.WeatherWidget
import com.li.weatherapp.utils.Constants
import com.li.weatherapp.utils.showToast
import com.li.weatherapp.utils.withUnit
import java.lang.Exception

class WidgetUpdateService : JobIntentService() {

    private var remoteViews: RemoteViews? = null

    override fun onHandleWork(intent: Intent) {
        val lat = intent.getStringExtra(Constants.EXTRA_LATITUDE)
        val lon = intent.getStringExtra(Constants.EXTRA_LONGITUDE)
        val currentWeatherRepository =
            CurrentWeatherRepository.getInstance(CurrentWeatherRemoteDataSource.getInstance())
        if (lat != null && lon != null) {
            currentWeatherRepository.getCurrentWeatherForecast(
                lat,
                lon,
                object : OnDataLoadCallback<CurrentWeather> {
                    override fun onSuccess(data: CurrentWeather) {
                        updateWidget(data)
                        val widget =
                            ComponentName(this@WidgetUpdateService, WeatherWidget::class.java)
                        AppWidgetManager.getInstance(this@WidgetUpdateService)
                            .updateAppWidget(widget, remoteViews)
                    }

                    override fun onFail(exception: Exception) {
                        this@WidgetUpdateService.showToast(exception.message.toString())
                    }
                })
        }
    }

    private fun updateWidget(weather: CurrentWeather) {
        remoteViews = RemoteViews(packageName, R.layout.widget_weather)
        remoteViews?.apply {
            setTextViewText(
                R.id.textDescriptionWidget,
                weather.currentWeather.main.capitalize()
            )
            setTextViewText(
                R.id.textTemperatureWidget,
                weather.currentTemp.currentTemp.toInt().toString()
            )
            setTextViewText(
                R.id.textMinMaxTemperatureWidget,
                formatMinMaxTemperature(
                    weather.currentTemp.min.toInt(),
                    weather.currentTemp.max.toInt()
                )
            )
            setTextViewText(
                R.id.textHumidityWidget,
                weather.currentTemp.humidity.toInt().withUnit(Constants.DEFAULT_PERCENT)
            )
            setTextViewText(
                R.id.textWindWidget,
                weather.wind.speed.toInt().withUnit(Constants.DEFAULT_KILOMETER)
            )
        }
    }

    private fun formatMinMaxTemperature(min: Int, max: Int) =
        "${min.withUnit(Constants.DEFAULT_CELSIUS)} - ${max.withUnit(Constants.DEFAULT_CELSIUS)}"

    companion object {
        private const val JOB_ID = 998

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, WidgetUpdateService::class.java, JOB_ID, intent)
        }
    }
}
