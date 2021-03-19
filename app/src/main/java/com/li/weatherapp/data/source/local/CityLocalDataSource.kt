package com.li.weatherapp.data.source.local

import com.li.weatherapp.data.model.City
import com.li.weatherapp.data.source.CityDataSource
import com.li.weatherapp.data.source.local.dao.FavoriteCityDAO
import com.li.weatherapp.data.source.local.utils.LocalAsyncTask
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

@Suppress("DEPRECATION")
class CityLocalDataSource private constructor(
    private val favoriteCityDAO: FavoriteCityDAO
) : CityDataSource.Local {

    override fun insertFavoriteCity(city: City, callback: OnDataLoadCallback<Boolean>) {
        LocalAsyncTask<City, Boolean>(callback) {
            favoriteCityDAO.insertFavoriteCity(city)
        }.execute(city)
    }

    override fun deleteFavoriteCity(id: String, callback: OnDataLoadCallback<Boolean>) {
        LocalAsyncTask<String, Boolean>(callback) {
            favoriteCityDAO.deleteFavoriteCity(id)
        }.execute(id)
    }

    override fun getFavoriteCities(callback: OnDataLoadCallback<MutableList<City>>) {
        LocalAsyncTask<Unit, MutableList<City>>(callback) {
            favoriteCityDAO.getFavoriteCities()
        }.execute(Unit)
    }
}
