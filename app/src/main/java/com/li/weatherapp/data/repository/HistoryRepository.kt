package com.li.weatherapp.data.repository

import com.li.weatherapp.data.model.History
import com.li.weatherapp.data.source.HistoryDataSource
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

class HistoryRepository(private val remote: HistoryDataSource) : HistoryDataSource {

    override fun getHistory(lat: String, lon: String, callback: OnDataLoadCallback<List<History>>) {
        remote.getHistory(lat, lon, callback)
    }

    companion object {
        private var instance: HistoryRepository? = null
        fun getInstance(remote: HistoryDataSource) =
            instance ?: HistoryRepository(remote).also { instance = it }
    }
}
