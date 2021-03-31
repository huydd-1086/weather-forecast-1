package com.li.weatherapp.data.source.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.li.weatherapp.data.model.City

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
        private const val DATABASE_NAME = "weather"
        private const val DATABASE_VERSION = 1
        private const val CREATE_TABLE_CITY =
            "CREATE TABLE ${City.CITY_TABLE_NAME} (" +
                "${City.CITY_ID} INTEGER PRIMARY KEY, " +
                "${City.CITY_NAME} TEXT, " +
                "${City.CITY_LATITUDE} REAL, " +
                "${City.CITY_LONGITUDE} REAL)"
        private const val DROP_TABLE_CITY =
            "DROP TABLE IF EXIST " + City.CITY_TABLE_NAME
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) =
            instance ?: AppDatabase(context, DATABASE_NAME, DATABASE_VERSION).also {
                instance = it
            }
    }
}
