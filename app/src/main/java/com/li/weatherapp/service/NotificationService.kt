package com.li.weatherapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import com.li.weatherapp.R
import com.li.weatherapp.data.model.CurrentWeather
import com.li.weatherapp.data.repository.CurrentWeatherRepository
import com.li.weatherapp.data.source.remote.CurrentWeatherRemoteDataSource
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import com.li.weatherapp.ui.main.MainActivity
import com.li.weatherapp.utils.Constants
import com.li.weatherapp.utils.showToast
import java.lang.Exception

class NotificationService : JobIntentService() {

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
                        createNotification(data)
                    }

                    override fun onFail(exception: Exception) {
                        this@NotificationService.showToast(exception.message.toString())
                    }
                })
        }
    }

    private fun createRemoteViews(weather: CurrentWeather) {
        remoteViews = RemoteViews(packageName, R.layout.layout_notification)
        remoteViews?.apply {
            setTextViewText(
                R.id.textDescriptionNotification,
                weather.currentWeather.description.capitalize()
            )
            setTextViewText(
                R.id.textTemperatureNotification,
                weather.currentTemp.currentTemp.toInt().toString()
            )
            setTextViewText(
                R.id.textTemperatureMaxNotification,
                weather.currentTemp.max.toInt().toString()
            )
            setTextViewText(
                R.id.textTemperatureMinNotification,
                weather.currentTemp.min.toInt().toString()
            )
            setTextViewText(
                R.id.textHumidityNotification,
                weather.currentTemp.humidity.toInt().toString()
            )
            setTextViewText(
                R.id.textWindNotification,
                weather.wind.speed.toInt().toString()
            )
        }
    }

    private fun createNotification(weather: CurrentWeather) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH)
            channel.apply {
                enableVibration(true)
                enableLights(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
        createRemoteViews(weather)
        val intent = MainActivity.getIntentFromNotification(this)
        val pendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_weather_place_holder)
            setContentIntent(pendingIntent)
            setCustomBigContentView(remoteViews)
            setAutoCancel(true)
            priority = NotificationCompat.PRIORITY_HIGH
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    companion object {
        private const val CHANNEL_ID = "NotifyDailyWeatherChannel"
        private const val JOB_ID = 999
        private const val NOTIFICATION_ID = 9999

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, NotificationService::class.java, JOB_ID, intent)
        }
    }
}
