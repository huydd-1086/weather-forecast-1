package com.li.weatherapp.data.model

import android.os.Parcelable
import com.li.weatherapp.utils.mapToListObject
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class History(
    val time: Long,
    val temp: Long,
    val humidity: Long,
    val wind: Double,
    val weather: List<Weather>
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(TIME),
        jsonObject.getLong(TEMP),
        jsonObject.getLong(HUMIDITY),
        jsonObject.getDouble(WIND_SPEED),
        jsonObject.getJSONArray(WEATHER).mapToListObject(::Weather)
    )

    companion object {
        const val CURRENT="current"
        private const val TIME = "dt"
        private const val WIND_SPEED = "wind_speed"
        private const val TEMP = "temp"
        private const val HUMIDITY = "humidity"
        private const val WEATHER = "weather"
    }
}
