package com.li.weatherapp.ui.hourly

import com.li.weatherapp.base.BasePresenter
import com.li.weatherapp.base.BaseView
import com.li.weatherapp.data.model.Hourly

interface HourlyForeCastContract {
    interface View : BaseView {
        fun showHourlyForecast(hourlyForecastList: List<Hourly>)
        fun updateLocation(lat: String, lon: String)
    }

    interface Presenter : BasePresenter {
        fun getHourlyForecast(lat: String, lon: String)
        fun getCurrentLocation()
    }
}
