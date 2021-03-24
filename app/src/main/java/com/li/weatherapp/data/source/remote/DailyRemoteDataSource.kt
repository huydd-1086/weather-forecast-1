package com.li.weatherapp.data.source.remote

import com.li.weatherapp.data.model.Daily
import com.li.weatherapp.data.source.DailyDataSource
import com.li.weatherapp.data.source.remote.api.APIQueries
import com.li.weatherapp.data.source.remote.utils.RemoteAsyncTask
import com.li.weatherapp.data.source.remote.utils.makeNetworkCall
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import com.li.weatherapp.utils.mapToListObject
import org.json.JSONObject

@Suppress("DEPRECATION")
class DailyRemoteDataSource private constructor() : DailyDataSource {

    override fun getDailyForecast(
        lat: String,
        lon: String,
        callback: OnDataLoadCallback<List<Daily>>
    ) {
        RemoteAsyncTask(callback) {
            getDailyForecast(lat, lon)
        }.execute()
    }

    private fun getDailyForecast(lat: String, lon: String): List<Daily> {
        val jsonString = makeNetworkCall(
            APIQueries.queryDailyForecast(lat, lon)
        )
        return JSONObject(jsonString).getJSONArray(Daily.DAILY).mapToListObject(::Daily)
    }

    companion object {
        private var instance: DailyRemoteDataSource? = null
        fun getInstance() = instance ?: DailyRemoteDataSource().also { instance = it }
    }
}
