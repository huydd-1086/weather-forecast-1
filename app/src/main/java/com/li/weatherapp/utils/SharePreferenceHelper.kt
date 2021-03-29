package com.li.weatherapp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.li.weatherapp.R

class SharePreferenceHelper private constructor(context: Context) {
    private var sharedPreferences =
        context?.getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE)

    fun <T> put(key: String, data: T) {
        val editor = sharedPreferences?.edit()
        when (data) {
            is String -> editor?.putString(key, data)
            is Double -> editor?.putString(key, data.toString())
        }
        editor?.apply()
    }

    fun <T> get(key: String, data: T): T = when (data) {
        is String -> sharedPreferences.getString(key, null) as T
        is Double -> sharedPreferences.getString(key, Constants.DEFAULT_STRING) as T
        else -> throw TypeCastException(R.string.text_error_not_type.toString())
    }

    companion object {
        private var instance: SharePreferenceHelper? = null
        fun getInstance(context: Context) =
            instance ?: SharePreferenceHelper(context).also { instance = it }
    }
}
