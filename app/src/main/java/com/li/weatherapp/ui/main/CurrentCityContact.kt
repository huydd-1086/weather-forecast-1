package com.li.weatherapp.ui.main

import com.li.weatherapp.base.BasePresenter

interface CurrentCityContact {
    interface Presenter : BasePresenter {
        fun setLatitude(lat: Double)
        fun setLongitude(lon: Double)
    }
}
