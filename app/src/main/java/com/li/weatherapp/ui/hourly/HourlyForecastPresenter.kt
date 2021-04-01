package com.li.weatherapp.ui.hourly

import com.li.weatherapp.data.model.Hourly
import com.li.weatherapp.data.repository.CurrentCityRepository
import com.li.weatherapp.data.repository.HourlyRepository
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import java.lang.Exception

class HourlyForecastPresenter(
    private val view: HourlyForeCastContract.View,
    private val hourlyRepository: HourlyRepository,
    private val currentCityRepository: CurrentCityRepository
) : HourlyForeCastContract.Presenter {

    override fun getHourlyForecast(lat: String, lon: String) {
        hourlyRepository.getHourlyForecast(lat, lon, object : OnDataLoadCallback<List<Hourly>> {
            override fun onSuccess(data: List<Hourly>) {
                view.showHourlyForecast(data)
            }

            override fun onFail(exception: Exception) {
                view.showMessage(exception.message.toString())
            }
        })
    }

    override fun getCurrentLocation() {
        view.updateLocation(
            currentCityRepository.getLatitude(),
            currentCityRepository.getLongitude()
        )
    }

    override fun start() {
    }
}
