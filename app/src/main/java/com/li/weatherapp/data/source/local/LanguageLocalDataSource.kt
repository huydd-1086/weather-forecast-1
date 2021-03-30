package com.li.weatherapp.data.source.local

import com.li.weatherapp.data.source.LanguageDataSource
import com.li.weatherapp.utils.Constants
import com.li.weatherapp.utils.SharePreferenceHelper

class LanguageLocalDataSource private constructor(
    private val preferencesHelper: SharePreferenceHelper
) : LanguageDataSource {

    override fun getLanguage(): String = preferencesHelper.get(Constants.LANGUAGE_KEY, String())

    override fun putLanguage(languageCode: String) {
        preferencesHelper.put(Constants.LANGUAGE_KEY, languageCode)
    }

    companion object {
        private var instance: LanguageLocalDataSource? = null

        fun getInstance(preferencesHelper: SharePreferenceHelper) =
            instance ?: LanguageLocalDataSource(preferencesHelper).also {
                instance = it
            }
    }
}
