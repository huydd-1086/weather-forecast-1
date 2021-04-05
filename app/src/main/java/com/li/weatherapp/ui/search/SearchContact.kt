package com.li.weatherapp.ui.search

import com.li.weatherapp.base.BasePresenter
import com.li.weatherapp.base.BaseView
import com.li.weatherapp.data.model.SearchedCity

interface SearchContact {
    interface View : BaseView {
        fun showSearchedCities(cities: List<SearchedCity>)
        fun showRecentCities(cities: List<SearchedCity>)
        fun showInfoInsertRecentCity(isInsert: Boolean)
        fun showInfoInsertFavoriteCity(isInsert: Boolean)
        fun showDeleteFavoriteInfo(isDelete: Boolean)
    }

    interface Presenter : BasePresenter {
        fun getSearchedCities(cityName: String)
        fun getRecentCities()
        fun addCityToRecentCities(city: SearchedCity)
        fun addCityToFavoriteCities(city: SearchedCity)
        fun deleteFavoriteCity(city: SearchedCity)
        fun setCityName(cityName: String)
        fun setLatitude(lat: String)
        fun setLongitude(lon: String)
    }
}
