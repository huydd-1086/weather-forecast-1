package com.li.weatherapp.data.repository

import com.li.weatherapp.data.model.Daily
import com.li.weatherapp.data.source.DailyDataSource
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

class DailyRepository private constructor(private val remote: DailyDataSource) : DailyDataSource {

    override fun getDailyForecast(
        lat: String,
        lon: String,
        callback: OnDataLoadCallback<List<Daily>>
    ) {
        remote.getDailyForecast(lat, lon, callback)
    }

    companion object {
        private var instance: DailyRepository? = null
        fun getInstance(remote: DailyDataSource) =
            instance ?: DailyRepository(remote).also { instance = it }
    }
}
