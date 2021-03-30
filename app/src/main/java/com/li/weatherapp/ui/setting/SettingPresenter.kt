package com.li.weatherapp.ui.setting

import com.li.weatherapp.data.repository.CurrentCityRepository
import com.li.weatherapp.data.repository.LanguageRepository

class SettingPresenter(
    private val view: SettingContract.View,
    private val languageRepository: LanguageRepository,
    private val currentCityRepository: CurrentCityRepository
) : SettingContract.Presenter {

    override fun putLanguage(languageCode: String) {
        languageRepository.putLanguage(languageCode)
        view.changeLanguage(languageRepository.getLanguage())
    }

    override fun getLanguage(): String {
        view.changeLanguage(languageRepository.getLanguage())
        return languageRepository.getLanguage()
    }

    override fun getLatitude() = currentCityRepository.getLatitude()

    override fun getLongitude() = currentCityRepository.getLongitude()

    override fun start() {
        getLanguage()
    }
}
