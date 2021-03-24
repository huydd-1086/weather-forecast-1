package com.li.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class RealFeeling(
    val day: Double
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getDouble(REAL_FEELING_DAY)
    )

    companion object {
        const val REAL_FEELING_DAY = "day"
    }
}
