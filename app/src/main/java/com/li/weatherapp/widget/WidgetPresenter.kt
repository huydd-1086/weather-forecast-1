package com.li.weatherapp.widget

import com.li.weatherapp.data.repository.CurrentCityRepository

class WidgetPresenter(
    private val currentCityRepository: CurrentCityRepository
) : WidgetContract.Presenter {

    override fun getLatitude() = currentCityRepository.getLatitude()

    override fun getLongitude() = currentCityRepository.getLongitude()

    override fun start() {
    }
}
