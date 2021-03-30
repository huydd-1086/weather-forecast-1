package com.li.weatherapp.ui.currentweather

import com.li.weatherapp.base.BasePresenter
import com.li.weatherapp.base.BaseView
import com.li.weatherapp.data.model.AQI
import com.li.weatherapp.data.model.CurrentWeather

interface CurrentWeatherForecastContact {
    interface View : BaseView {
        fun showCurrentWeatherForecast(weather: CurrentWeather)
        fun showAQIForecast(airQuality: AQI)
        fun updateLocation(lat: String, lon: String)
    }

    interface Presenter : BasePresenter {
        fun getCurrentWeatherForecast(lat: String, lon: String)
        fun getAQI(lat: String, lon: String)
        fun getCurrentLocation()
        fun setCityName(cityName: String)
    }
}
