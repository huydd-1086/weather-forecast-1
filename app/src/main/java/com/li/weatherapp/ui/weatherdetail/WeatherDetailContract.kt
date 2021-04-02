package com.li.weatherapp.ui.weatherdetail

import com.li.weatherapp.base.BasePresenter
import com.li.weatherapp.base.BaseView
import com.li.weatherapp.data.model.History

interface WeatherDetailContract {
    interface View : BaseView {
        fun showHistory(historyList: List<History>)
    }

    interface Presenter : BasePresenter {
        fun getHistory(lat: String, lon: String)
    }
}
