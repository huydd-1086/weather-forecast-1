package com.li.weatherapp.data.source

import com.li.weatherapp.data.model.SearchedCity
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

interface SearchedCityDataSource {
    interface Remote {
        fun getCities(cityName: String, callback: OnDataLoadCallback<List<SearchedCity>>)
    }

    interface Local {
        fun insertRecentCity(city: SearchedCity, callback: OnDataLoadCallback<Boolean>)
        fun deleteFavoriteCity(city: SearchedCity, callback: OnDataLoadCallback<Boolean>)
        fun insertFavoriteCity(city: SearchedCity, callback: OnDataLoadCallback<Boolean>)
        fun getRecentCities(callback: OnDataLoadCallback<List<SearchedCity>>)
    }

}
