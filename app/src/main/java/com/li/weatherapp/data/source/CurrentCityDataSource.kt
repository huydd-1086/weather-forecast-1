package com.li.weatherapp.data.source

interface CurrentCityDataSource {
    fun setLatitude(lat: Double)
    fun setLongitude(lon: Double)
    fun setCityName(cityName: String)
    fun getLatitude(): String
    fun getLongitude(): String
    fun getCityName(): String
}
