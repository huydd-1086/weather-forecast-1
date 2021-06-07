package com.li.weatherapp.data.source.local

import com.li.weatherapp.data.model.SearchedCity
import com.li.weatherapp.data.source.SearchedCityDataSource
import com.li.weatherapp.data.source.local.dao.FavoriteCityDAO
import com.li.weatherapp.data.source.local.utils.LocalAsyncTask
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

class SearchedCityLocalDataSource private constructor(
    private val favoriteCityDAO: FavoriteCityDAO
) : SearchedCityDataSource.Local {

    override fun insertRecentCity(city: SearchedCity, callback: OnDataLoadCallback<Boolean>) {
        LocalAsyncTask<SearchedCity, Boolean>(callback) {
            favoriteCityDAO.insertFavoriteCity(city)
        }.execute(city)
    }

    override fun deleteFavoriteCity(city: SearchedCity, callback: OnDataLoadCallback<Boolean>) {
        LocalAsyncTask<SearchedCity, Boolean>(callback) {
            favoriteCityDAO.updateFavoriteCity(city)
        }.execute(city)
    }

    override fun insertFavoriteCity(city: SearchedCity, callback: OnDataLoadCallback<Boolean>) {
        LocalAsyncTask<SearchedCity, Boolean>(callback) {
            favoriteCityDAO.updateFavoriteCity(city)
        }.execute(city)
    }

    override fun getRecentCities(callback: OnDataLoadCallback<List<SearchedCity>>) {
        LocalAsyncTask<Unit, List<SearchedCity>>(callback) {
            favoriteCityDAO.getRecentCities()
        }.execute(Unit)
    }

    override fun getFavoriteCities(callback: OnDataLoadCallback<List<SearchedCity>>) {
        LocalAsyncTask<Unit, List<SearchedCity>>(callback) {
            favoriteCityDAO.getFavoriteCities()
        }.execute(Unit)
    }

    companion object {
        private var instance: SearchedCityLocalDataSource? = null
        fun getInstance(favoriteDao: FavoriteCityDAO) =
            instance ?: SearchedCityLocalDataSource(favoriteDao).also { instance = it }
    }
}
