package com.li.weatherapp.data.source.remote

import com.li.weatherapp.data.model.Hourly
import com.li.weatherapp.data.source.HourlyDataSource
import com.li.weatherapp.data.source.remote.api.APIQueries
import com.li.weatherapp.data.source.remote.utils.RemoteAsyncTask
import com.li.weatherapp.data.source.remote.utils.makeNetworkCall
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import com.li.weatherapp.utils.mapToListObject
import org.json.JSONObject

class HourlyRemoteDataSource private constructor() : HourlyDataSource {

    override fun getHourlyForecast(
        lat: String,
        lon: String,
        callback: OnDataLoadCallback<List<Hourly>>
    ) {
        RemoteAsyncTask(callback) {
            getHourlyForecast(lat, lon)
        }.execute()
    }

    private fun getHourlyForecast(lat: String, lon: String): List<Hourly> {
        val jsonString = makeNetworkCall(
            APIQueries.queryHourlyForecast(lat, lon)
        )
        return JSONObject(jsonString)
            .getJSONArray(Hourly.HOURLY)
            .mapToListObject(::Hourly)
    }

    companion object {
        private var instance: HourlyRemoteDataSource? = null
        fun getInstance() = instance ?: HourlyRemoteDataSource().also { instance = it }
    }
}
