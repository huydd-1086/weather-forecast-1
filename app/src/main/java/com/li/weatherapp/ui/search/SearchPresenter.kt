package com.li.weatherapp.ui.search

import com.li.weatherapp.data.model.SearchedCity
import com.li.weatherapp.data.repository.CurrentCityRepository
import com.li.weatherapp.data.repository.SearchedCityRepository
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import java.lang.Exception

class SearchPresenter(
    private val view: SearchContact.View,
    private val searchedCityRepository: SearchedCityRepository,
    private val currentCityRepository: CurrentCityRepository
) : SearchContact.Presenter {
    override fun getSearchedCities(cityName: String) {
        searchedCityRepository.getCities(cityName, object : OnDataLoadCallback<List<SearchedCity>> {
            override fun onSuccess(data: List<SearchedCity>) {
                view.showSearchedCities(data)
            }

            override fun onFail(exception: Exception) {
                view.showMessage(exception.toString())
            }
        })
    }

    override fun getRecentCities() {
        searchedCityRepository.getRecentCities(object : OnDataLoadCallback<List<SearchedCity>> {
            override fun onSuccess(data: List<SearchedCity>) {
                view.showRecentCities(data)
            }

            override fun onFail(exception: Exception) {
                view.showMessage(exception.toString())
            }
        })
    }

    override fun addCityToRecentCities(city: SearchedCity) {
        searchedCityRepository.insertRecentCity(city, object : OnDataLoadCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.showInfoInsertRecentCity(data)
            }

            override fun onFail(exception: Exception) {
                view.showMessage(exception.toString())
            }
        })
    }

    override fun addCityToFavoriteCities(city: SearchedCity) {
        searchedCityRepository.insertFavoriteCity(city, object : OnDataLoadCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.showInfoInsertFavoriteCity(data)
            }

            override fun onFail(exception: Exception) {
                view.showMessage(exception.toString())
            }
        })
    }

    override fun deleteFavoriteCity(city: SearchedCity) {
        searchedCityRepository.deleteFavoriteCity(city, object : OnDataLoadCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.showDeleteFavoriteInfo(data)
            }

            override fun onFail(exception: Exception) {
                view.showMessage(exception.toString())
            }
        })
    }

    override fun setCityName(cityName: String) {
        currentCityRepository.setCityName(cityName)
    }

    override fun setLatitude(lat: String) {
        currentCityRepository.setLatitude(lat.toDouble())
    }

    override fun setLongitude(lon: String) {
        currentCityRepository.setLongitude(lon.toDouble())
    }

    override fun start() {
        getRecentCities()
    }
}
