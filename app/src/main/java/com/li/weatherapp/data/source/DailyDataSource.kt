package com.li.weatherapp.data.source

import com.li.weatherapp.data.model.Daily
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

interface DailyDataSource {
    fun getDailyForecast(lat: String, lon: String, callback: OnDataLoadCallback<List<Daily>>)
}
