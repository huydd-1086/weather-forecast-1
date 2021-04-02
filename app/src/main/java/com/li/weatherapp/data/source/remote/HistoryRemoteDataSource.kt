package com.li.weatherapp.data.source.remote

import android.text.format.DateUtils
import com.li.weatherapp.data.model.History
import com.li.weatherapp.data.source.HistoryDataSource
import com.li.weatherapp.data.source.remote.api.APIQueries
import com.li.weatherapp.data.source.remote.utils.RemoteAsyncTask
import com.li.weatherapp.data.source.remote.utils.makeNetworkCall
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import com.li.weatherapp.utils.Constants
import org.json.JSONObject

class HistoryRemoteDataSource private constructor() : HistoryDataSource {

    override fun getHistory(lat: String, lon: String, callback: OnDataLoadCallback<List<History>>) {
        RemoteAsyncTask(callback) {
            getHistoryList(lat, lon)
        }.execute()
    }

    private fun getHistory(lat: String, lon: String, dayQuantity: Int): History {
        val time =
            (System.currentTimeMillis() - DateUtils.DAY_IN_MILLIS * dayQuantity).div(DateUtils.SECOND_IN_MILLIS)
        val jsonString = makeNetworkCall(
            APIQueries.queryHistory(
                lat,
                lon,
                time.toString()
            )
        )
        return History(JSONObject(jsonString).getJSONObject(History.CURRENT))
    }

    private fun getHistoryList(lat: String, lon: String): List<History> {
        val historyList = mutableListOf<History>()
        for (i in 1..5) {
            historyList.add(getHistory(lat, lon, i))
        }
        return historyList
    }

    companion object {
        private var instance: HistoryRemoteDataSource? = null
        fun getInstance() = instance ?: HistoryRemoteDataSource().also { instance = it }
    }
}
