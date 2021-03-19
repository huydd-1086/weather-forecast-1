package com.li.weatherapp.data.repository

import com.li.weatherapp.data.model.City
import com.li.weatherapp.data.source.CityDataSource
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

class CityRepository private constructor(
    private val local: CityDataSource.Local
) : CityDataSource.Local {

    override fun insertFavoriteCity(city: City, callback: OnDataLoadCallback<Boolean>) {
        local.insertFavoriteCity(city, callback)
    }

    override fun deleteFavoriteCity(id: String, callback: OnDataLoadCallback<Boolean>) {
        local.deleteFavoriteCity(id, callback)
    }

    override fun getFavoriteCities(callback: OnDataLoadCallback<MutableList<City>>) {
        local.getFavoriteCities(callback)
    }
}
