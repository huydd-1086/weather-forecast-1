package com.li.weatherapp.data.repository

import com.li.weatherapp.data.model.SearchedCity
import com.li.weatherapp.data.source.SearchedCityDataSource
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

class SearchedCityRepository private constructor(
    private val remote: SearchedCityDataSource.Remote,
    private val local: SearchedCityDataSource.Local
) : SearchedCityDataSource.Local, SearchedCityDataSource.Remote {

    override fun getCities(cityName: String, callback: OnDataLoadCallback<List<SearchedCity>>) {
        remote.getCities(cityName, callback)
    }

    override fun insertRecentCity(city: SearchedCity, callback: OnDataLoadCallback<Boolean>) {
        local.insertRecentCity(city, callback)
    }

    override fun deleteFavoriteCity(city: SearchedCity, callback: OnDataLoadCallback<Boolean>) {
        local.deleteFavoriteCity(city, callback)
    }

    override fun insertFavoriteCity(city: SearchedCity, callback: OnDataLoadCallback<Boolean>) {
        local.insertFavoriteCity(city, callback)
    }

    override fun getRecentCities(callback: OnDataLoadCallback<List<SearchedCity>>) {
        local.getRecentCities(callback)
    }

    override fun getFavoriteCities(callback: OnDataLoadCallback<List<SearchedCity>>) {
        local.getFavoriteCities(callback)
    }

    companion object {
        private var instance: SearchedCityRepository? = null

        fun getInstance(
            remote: SearchedCityDataSource.Remote,
            local: SearchedCityDataSource.Local
        ) = instance ?: SearchedCityRepository(remote, local).also { instance = it }
    }
}
