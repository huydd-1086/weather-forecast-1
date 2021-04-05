package com.li.weatherapp.data.source.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.li.weatherapp.data.model.SearchedCity

class AppDatabase private constructor(
    context: Context,
    name: String,
    version: Int
) : SQLiteOpenHelper(context, name, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_CITY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE_CITY)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "weather.db"
        private const val DATABASE_VERSION = 2
        private const val CREATE_TABLE_CITY =
            "CREATE TABLE ${SearchedCity.CITY_TABLE_NAME} (" +
                "${SearchedCity.CITY_NAME} TEXT, " +
                "${SearchedCity.CITY_LATITUDE} TEXT, " +
                "${SearchedCity.CITY_LONGITUDE} TEXT, " +
                "${SearchedCity.CITY_IS_FAVORITE} NUMBER(1))"
        private const val DROP_TABLE_CITY =
            "DROP TABLE IF EXIST " + SearchedCity.CITY_TABLE_NAME

        private val lock = Any()
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(lock) {
                instance ?: AppDatabase(context, DATABASE_NAME, DATABASE_VERSION).also {
                    instance = it
                }
            }
    }
}
