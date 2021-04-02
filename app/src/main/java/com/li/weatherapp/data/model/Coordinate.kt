package com.li.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Coordinate(
    val lat: Double,
    val lon: Double
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getDouble(LATITUDE),
        jsonObject.getDouble(LONGITUDE)
    )

    companion object {
        private const val LATITUDE = "lat"
        private const val LONGITUDE = "lon"
    }
}
