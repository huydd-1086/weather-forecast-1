package com.li.weatherapp.data.repository

import com.li.weatherapp.data.model.Hourly
import com.li.weatherapp.data.source.HourlyDataSource
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

class HourlyRepository private constructor(
    private val remote: HourlyDataSource
) : HourlyDataSource {

    override fun getHourlyForecast(
        lat: String,
        lon: String,
        callback: OnDataLoadCallback<List<Hourly>>
    ) {
        remote.getHourlyForecast(lat, lon, callback)
    }

    companion object {
        private var instance: HourlyRepository? = null
        fun getInstance(remote: HourlyDataSource) =
            instance ?: HourlyRepository(remote).also { instance = it }
    }
}

