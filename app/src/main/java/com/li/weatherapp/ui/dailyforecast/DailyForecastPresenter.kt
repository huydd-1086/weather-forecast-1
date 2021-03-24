package com.li.weatherapp.ui.dailyforecast

import com.li.weatherapp.data.model.Daily
import com.li.weatherapp.data.repository.DailyRepository
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import java.lang.Exception

class DailyForecastPresenter(
    private val view: DailyForecastContract.View,
    private val dailyRepository: DailyRepository
) : DailyForecastContract.Presenter {

    override fun getDailyForecast(lat: String, lon: String) {
        dailyRepository.getDailyForecast(lat, lon, object : OnDataLoadCallback<List<Daily>> {
            override fun onSuccess(data: List<Daily>) {
                view.showDailyForecast(data)
            }

            override fun onFail(exception: Exception) {
                view.showMessage(exception.message.toString())
            }
        })
    }

    override fun start() {
    }
}
