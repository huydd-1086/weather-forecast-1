package com.li.weatherapp.data.source

interface LanguageDataSource {
    fun putLanguage(languageCode: String)
    fun getLanguage(): String
}
