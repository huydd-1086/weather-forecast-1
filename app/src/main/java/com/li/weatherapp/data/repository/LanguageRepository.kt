package com.li.weatherapp.data.repository

import com.li.weatherapp.data.source.LanguageDataSource

class LanguageRepository private constructor(
    private val local: LanguageDataSource
) : LanguageDataSource {

    override fun putLanguage(languageCode: String) {
        local.putLanguage(languageCode)
    }

    override fun getLanguage() = local.getLanguage()

    companion object {
        private var instance: LanguageRepository? = null
        fun getInstance(local: LanguageDataSource) =
            instance ?: LanguageRepository(local).also { instance = it }
    }
}
