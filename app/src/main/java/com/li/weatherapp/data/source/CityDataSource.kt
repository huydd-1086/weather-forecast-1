package com.li.weatherapp.data.source

import com.li.weatherapp.data.model.City
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

interface CityDataSource {
    interface Local {
        fun insertFavoriteCity(city: City, callback: OnDataLoadCallback<Boolean>)
        fun deleteFavoriteCity(id: String, callback: OnDataLoadCallback<Boolean>)
        fun getFavoriteCities(callback: OnDataLoadCallback<MutableList<City>>)
    }
}
