package com.li.weatherapp.data.source

import com.li.weatherapp.data.model.Hourly
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

interface HourlyDataSource {
    fun getHourlyForecast(lat: String, lon: String, callback: OnDataLoadCallback<List<Hourly>>)
}
