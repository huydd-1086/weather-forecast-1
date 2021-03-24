package com.li.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Weather(
    val main: String,
    val description: String
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(WEATHER_MAIN),
        jsonObject.getString(WEATHER_DESCRIPTION)
    )

    companion object {
        const val WEATHER_MAIN = "main"
        const val WEATHER_DESCRIPTION = "description"
    }
}
