package com.li.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Temp (
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getDouble(TEMP_DAY),
        jsonObject.getDouble(TEMP_MIN),
        jsonObject.getDouble(TEMP_MAX),
        jsonObject.getDouble(TEMP_NIGHT)
    )

    companion object {
        const val TEMP_DAY = "day"
        const val TEMP_MIN = "min"
        const val TEMP_MAX = "max"
        const val TEMP_NIGHT = "night"
    }
}
