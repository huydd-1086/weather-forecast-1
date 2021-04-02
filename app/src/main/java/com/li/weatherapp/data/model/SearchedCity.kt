package com.li.weatherapp.data.model

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class SearchedCity(
    val name: String,
    val latitude: String,
    val longitude: String,
    var isFavorite: Int
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(CITY_NAME),
        jsonObject.getDouble(CITY_LATITUDE).toString(),
        jsonObject.getDouble(CITY_LONGITUDE).toString(),
        DEFAULT_FAVORITE
    )

    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(CITY_NAME)),
        cursor.getString(cursor.getColumnIndex(CITY_LATITUDE)),
        cursor.getString(cursor.getColumnIndex(CITY_LONGITUDE)),
        cursor.getInt(cursor.getColumnIndex(CITY_IS_FAVORITE))
    )

    fun getContentValues() = ContentValues().apply {
        put(CITY_NAME, name)
        put(CITY_LATITUDE, latitude)
        put(CITY_LONGITUDE, longitude)
        put(CITY_IS_FAVORITE, isFavorite)
    }

    companion object {
        const val CITY_TABLE_NAME = "city"
        const val SEARCHED_CITY = "data"
        const val CITY_NAME = "name"
        const val CITY_LATITUDE = "latitude"
        const val CITY_LONGITUDE = "longitude"
        const val CITY_IS_FAVORITE = "favorite"
        const val DEFAULT_FAVORITE = 0
    }
}
