package com.li.weatherapp.ui.setting

import com.li.weatherapp.base.BasePresenter
import com.li.weatherapp.base.BaseView

interface SettingContract {
    interface View : BaseView {
        fun changeLanguage(languageCode: String?)
    }

    interface Presenter : BasePresenter {
        fun putLanguage(languageCode: String)
        fun getLanguage(): String
        fun getLatitude(): String
        fun getLongitude(): String
    }
}
