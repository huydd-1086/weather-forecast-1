package com.li.weatherapp.ui.map

import com.li.weatherapp.base.BasePresenter
import com.li.weatherapp.base.BaseView

interface MapContract {
    interface Presenter: BasePresenter {
        fun setLatitude(lat: String)
        fun setLongitude(lon: String)
    }
}
