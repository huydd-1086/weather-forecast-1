package com.li.weatherapp.ui.favorite

import com.li.weatherapp.data.model.CurrentWeather
import com.li.weatherapp.data.model.SearchedCity
import com.li.weatherapp.data.repository.CurrentWeatherRepository
import com.li.weatherapp.data.repository.SearchedCityRepository
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import java.lang.Exception

class FavoritePresenter(
    private val view: FavoriteContact.View,
    private val favoriteRepository: SearchedCityRepository,
    private val weatherRepository: CurrentWeatherRepository
) : FavoriteContact.Presenter {

    override fun getFavoriteCities() {
        favoriteRepository.getFavoriteCities(object : OnDataLoadCallback<List<SearchedCity>> {
            override fun onSuccess(data: List<SearchedCity>) {
                getWeatherForecast(data)
            }

            override fun onFail(exception: Exception) {
                view.showMessage(exception.toString())
            }
        })
    }

    override fun getWeatherForecast(cities: List<SearchedCity>) {
        cities.forEach {
            weatherRepository.getCurrentWeatherForecast(
                it.latitude,
                it.longitude,
                object : OnDataLoadCallback<CurrentWeather> {
                    override fun onSuccess(data: CurrentWeather) {
                        view.showWeatherForecast(data)
                    }

                    override fun onFail(exception: Exception) {
                        view.showMessage(exception.toString())
                    }
                })
        }
    }

    override fun start() {
    }
}
