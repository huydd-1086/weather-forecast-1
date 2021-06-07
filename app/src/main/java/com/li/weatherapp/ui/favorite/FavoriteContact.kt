package com.li.weatherapp.ui.favorite

import com.li.weatherapp.base.BasePresenter
import com.li.weatherapp.base.BaseView
import com.li.weatherapp.data.model.CurrentWeather
import com.li.weatherapp.data.model.SearchedCity

interface FavoriteContact {
    interface View : BaseView {
        fun showWeatherForecast(weather: CurrentWeather)
    }

    interface Presenter : BasePresenter {
        fun getFavoriteCities()
        fun getWeatherForecast(cities: List<SearchedCity>)
    }
}
