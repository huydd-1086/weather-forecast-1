package com.li.weatherapp.data.model

import android.os.Parcelable
import com.li.weatherapp.utils.mapToListObject
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Daily(
    val time: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Temp,
    val feelsLike: RealFeeling,
    val pressure: Long,
    val humidity: Long,
    val windSpeed: Double,
    val windDeg: Long,
    val weather: List<Weather>,
    val clouds: Long,
    val uvi: Double
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(DAILY_TIME),
        jsonObject.getLong(DAILY_SUNRISE),
        jsonObject.getLong(DAILY_SUNSET),
        jsonObject.getJSONObject(DAILY_TEMP).let(::Temp),
        jsonObject.getJSONObject(DAILY_FEELS_LIKE).let(::RealFeeling),
        jsonObject.getLong(DAILY_PRESSURE),
        jsonObject.getLong(DAILY_HUMIDITY),
        jsonObject.getDouble(DAILY_WIND_SPEED),
        jsonObject.getLong(DAILY_WIND_DEG),
        jsonObject.getJSONArray(DAILY_WEATHER).mapToListObject(::Weather),
        jsonObject.getLong(DAILY_CLOUDS),
        jsonObject.getDouble(DAILY_UVI)
    )

    companion object {
        const val DAILY = "daily"
        const val DAILY_TIME = "dt"
        const val DAILY_SUNRISE = "sunrise"
        const val DAILY_SUNSET = "sunset"
        const val DAILY_TEMP = "temp"
        const val DAILY_FEELS_LIKE = "feels_like"
        const val DAILY_PRESSURE = "pressure"
        const val DAILY_HUMIDITY = "humidity"
        const val DAILY_WIND_SPEED = "wind_speed"
        const val DAILY_WIND_DEG = "wind_deg"
        const val DAILY_WEATHER = "weather"
        const val DAILY_CLOUDS = "clouds"
        const val DAILY_UVI = "uvi"
    }
}
