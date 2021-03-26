package com.li.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class AQI(
    val aqi: Int,
    val airComponents: AirComponents
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getJSONArray(LIST_COMPONENTS).getJSONObject(0).getJSONObject(AQI_MAIN)
            .getInt(AQI),
        jsonObject.getJSONArray(LIST_COMPONENTS).getJSONObject(0)
            .getJSONObject(AirComponents.COMPONENTS_NAME).let(::AirComponents)
    )

    companion object {
        const val AQI = "aqi"
        const val AQI_MAIN = "main"
        const val LIST_COMPONENTS = "list"
    }
}
