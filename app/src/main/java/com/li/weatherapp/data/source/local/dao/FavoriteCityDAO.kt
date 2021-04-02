package com.li.weatherapp.data.source.local.dao

import com.li.weatherapp.data.model.SearchedCity

interface FavoriteCityDAO {
    fun insertFavoriteCity(city: SearchedCity): Boolean
    fun updateFavoriteCity(city: SearchedCity): Boolean
    fun getFavoriteCities(): List<SearchedCity>
    fun getRecentCities(): List<SearchedCity>
}
