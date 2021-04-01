package com.li.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Hourly(
    val time: Long,
    val temp: Double,
    val feelsLike: Double,
    val pressure: Long,
    val humidity: Long,
    val windSpeed: Double,
    val windDeg: Long,
    val weather: Weather,
    val clouds: Long,
    val uvi: Double
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(HOURLY_TIME),
        jsonObject.getDouble(HOURLY_TEMP),
        jsonObject.getDouble(HOURLY_FEELS_LIKE),
        jsonObject.getLong(HOURLY_PRESSURE),
        jsonObject.getLong(HOURLY_HUMIDITY),
        jsonObject.getDouble(HOURLY_WIND_SPEED),
        jsonObject.getLong(HOURLY_WIND_DEG),
        jsonObject.getJSONArray(HOURLY_WEATHER).getJSONObject(0).let(::Weather),
        jsonObject.getLong(HOURLY_CLOUDS),
        jsonObject.getDouble(HOURLY_UVI)
    )

    companion object {
        const val HOURLY = "hourly"
        const val HOURLY_TIME = "dt"
        const val HOURLY_TEMP = "temp"
        const val HOURLY_FEELS_LIKE = "feels_like"
        const val HOURLY_PRESSURE = "pressure"
        const val HOURLY_HUMIDITY = "humidity"
        const val HOURLY_WIND_SPEED = "wind_speed"
        const val HOURLY_WIND_DEG = "wind_deg"
        const val HOURLY_WEATHER = "weather"
        const val HOURLY_CLOUDS = "clouds"
        const val HOURLY_UVI = "uvi"
    }
}
