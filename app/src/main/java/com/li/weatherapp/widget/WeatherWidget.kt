package com.li.weatherapp.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.li.weatherapp.R
import com.li.weatherapp.data.repository.CurrentCityRepository
import com.li.weatherapp.data.source.local.CurrentCityLocalDataSource
import com.li.weatherapp.service.WidgetUpdateService
import com.li.weatherapp.ui.main.MainActivity
import com.li.weatherapp.utils.Constants
import com.li.weatherapp.utils.SharePreferenceHelper

class WeatherWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
                .let { intent ->
                    PendingIntent.getActivity(context, 0, intent, 0)
                }
            val views = RemoteViews(
                context.packageName,
                R.layout.widget_weather
            ).apply {
                setOnClickPendingIntent(R.id.layoutWidget, pendingIntent)
            }
            val presenter = WidgetPresenter(
                CurrentCityRepository.getInstance(
                    CurrentCityLocalDataSource.getInstance(
                        SharePreferenceHelper.getInstance(context)
                    )
                )
            )
            val lat = presenter.getLatitude()
            val lon = presenter.getLongitude()
            val intent = Intent(context, this::class.java)
                .putExtra(Constants.EXTRA_LATITUDE, lat)
                .putExtra(Constants.EXTRA_LONGITUDE, lon)
            WidgetUpdateService.enqueueWork(context, intent)
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }
    }
}
