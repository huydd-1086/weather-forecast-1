package com.li.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Cloud(
    val coverage: Long
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(COVERAGE)
    )

    companion object {
        private const val COVERAGE = "all"
    }
}
