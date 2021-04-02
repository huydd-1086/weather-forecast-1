package com.li.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Sun(
    val sunrise: Long,
    val sunset: Long
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(SUNSET),
        jsonObject.getLong(SUNRISE)
    )

    companion object {
        private const val SUNSET = "sunset"
        private const val SUNRISE = "sunrise"
    }
}
