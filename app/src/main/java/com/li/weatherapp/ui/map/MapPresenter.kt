package com.li.weatherapp.ui.map

import com.li.weatherapp.data.repository.CurrentCityRepository

class MapPresenter(
    private val repository: CurrentCityRepository
) : MapContract.Presenter {

    override fun setLatitude(lat: String) {
        repository.setLatitude(lat.toDouble())
    }

    override fun setLongitude(lon: String) {
        repository.setLongitude(lon.toDouble())
    }

    override fun start() {
    }
}
