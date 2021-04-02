package com.li.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class CurrentWeather(
    val currentWeather: Weather,
    val currentTemp: CurrentTemp,
    val wind: Wind,
    val cityName: String,
    val cloud: Cloud,
    val sun: Sun,
    val visibility: Long,
    val coordinate: Coordinate
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getJSONArray(WEATHER_NAME).getJSONObject(0).let(::Weather),
        jsonObject.getJSONObject(CurrentTemp.CURRENT_TEMP_NAME).let(::CurrentTemp),
        jsonObject.getJSONObject(Wind.WIND_NAME).let(::Wind),
        jsonObject.getString(CITY_NAME),
        jsonObject.getJSONObject(CLOUD).let(::Cloud),
        jsonObject.getJSONObject(SUN).let(::Sun),
        jsonObject.getLong(VISIBILITY),
        jsonObject.getJSONObject(COORDINATE).let(::Coordinate)
    )

    companion object {
        const val WEATHER_NAME = "weather"
        const val CITY_NAME = "name"
        const val VISIBILITY = "visibility"
        const val CLOUD = "clouds"
        const val SUN = "sys"
        const val COORDINATE = "coord"
    }
}
