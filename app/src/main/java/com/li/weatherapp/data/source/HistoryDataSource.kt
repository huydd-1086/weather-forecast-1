package com.li.weatherapp.data.source

import com.li.weatherapp.data.model.History
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

interface HistoryDataSource {
    fun getHistory(lat: String, lon: String, callback: OnDataLoadCallback<List<History>>)
}
