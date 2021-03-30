package com.li.weatherapp.ui.main

import com.li.weatherapp.data.repository.CurrentCityRepository

class CurrentCityPresenter(
    private val repository: CurrentCityRepository
) : CurrentCityContact.Presenter {

    override fun setLatitude(lat: Double) {
        repository.setLatitude(lat)
    }

    override fun setLongitude(lon: Double) {
        repository.setLongitude(lon)
    }

    override fun start() {
    }
}
