package com.li.weatherapp.ui.weatherdetail

import com.li.weatherapp.data.model.History
import com.li.weatherapp.data.repository.HistoryRepository
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import java.lang.Exception

class WeatherDetailPresenter(
    private val view: WeatherDetailContract.View,
    private val repository: HistoryRepository
) : WeatherDetailContract.Presenter {

    override fun getHistory(lat: String, lon: String) {
        repository.getHistory(lat, lon, object : OnDataLoadCallback<List<History>> {
            override fun onSuccess(data: List<History>) {
                view.showHistory(data)
            }

            override fun onFail(exception: Exception) {
                view.showMessage(exception.message.toString())
            }
        })
    }

    override fun start() {
    }
}
