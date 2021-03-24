package com.li.weatherapp.ui.dailyforecast

import com.li.weatherapp.base.BasePresenter
import com.li.weatherapp.base.BaseView
import com.li.weatherapp.data.model.Daily

interface DailyForecastContract {
    interface View : BaseView {
        fun showDailyForecast(dailyForecastList: List<Daily>)
    }

    interface Presenter : BasePresenter {
        fun getDailyForecast(lat: String, lon: String)
    }
}
