package com.li.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class CurrentTemp(
    val currentTemp: Double,
    val realFeeling: Double,
    val min: Double,
    val max: Double,
    val pressure: Long,
    val humidity: Double
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getDouble(TEMP_CURRENT),
        jsonObject.getDouble(REAL_FEELING),
        jsonObject.getDouble(TEMP_MIN),
        jsonObject.getDouble(TEMP_MAX),
        jsonObject.getLong(PRESSURE),
        jsonObject.getDouble(HUMIDITY)
    )

    companion object {
        const val CURRENT_TEMP_NAME = "main"
        const val TEMP_CURRENT = "temp"
        const val REAL_FEELING = "feels_like"
        const val TEMP_MIN = "temp_min"
        const val TEMP_MAX = "temp_max"
        const val PRESSURE = "pressure"
        const val HUMIDITY = "humidity"
        const val COORDINATE = "coordinate"
    }
}
