package com.li.weatherapp.data.model

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable {

    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(CITY_ID)).toLong(),
        cursor.getString(cursor.getColumnIndex(CITY_NAME)),
        cursor.getString(cursor.getColumnIndex(CITY_LATITUDE)).toDouble(),
        cursor.getString(cursor.getColumnIndex(CITY_LONGITUDE)).toDouble()
    )

    fun getContentValues() = ContentValues().apply {
        put(CITY_ID, id)
        put(CITY_NAME, name)
        put(CITY_LATITUDE, latitude)
        put(CITY_LONGITUDE, latitude)
    }

    companion object {
        const val CITY_TABLE_NAME = "city"
        const val CITY_ID = "id"
        const val CITY_NAME = "name"
        const val CITY_LATITUDE = "latitude"
        const val CITY_LONGITUDE = "longitude"
    }
}
