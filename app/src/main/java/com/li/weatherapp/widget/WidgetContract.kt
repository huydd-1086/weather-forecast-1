package com.li.weatherapp.widget

import com.li.weatherapp.base.BasePresenter
import com.li.weatherapp.base.BaseView

interface WidgetContract {
    interface View : BaseView {
    }

    interface Presenter : BasePresenter {
        fun getLatitude(): String
        fun getLongitude(): String
    }
}
