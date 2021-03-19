package com.li.weatherapp.data.source.local.dao

import com.li.weatherapp.data.model.City

interface FavoriteCityDAO {
    fun insertFavoriteCity(city: City): Boolean
    fun deleteFavoriteCity(id: String): Boolean
    fun getFavoriteCities(): MutableList<City>
}
