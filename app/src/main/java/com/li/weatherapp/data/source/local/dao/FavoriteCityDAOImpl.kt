package com.li.weatherapp.data.source.local.dao

import android.annotation.SuppressLint
import com.li.weatherapp.data.model.City
import com.li.weatherapp.data.model.City.Companion.CITY_ID
import com.li.weatherapp.data.model.City.Companion.CITY_TABLE_NAME
import com.li.weatherapp.data.source.local.db.AppDatabase

class FavoriteCityDAOImpl private constructor(database: AppDatabase) : FavoriteCityDAO {

    private val writableDatabase = database.writableDatabase
    private val readableDatabase = database.readableDatabase

    override fun insertFavoriteCity(city: City): Boolean {
        return writableDatabase.insert(
            CITY_TABLE_NAME,
            null,
            city.getContentValues()
        ) > 0
    }

    override fun deleteFavoriteCity(id: String): Boolean {
        return writableDatabase.delete(
            CITY_TABLE_NAME,
            "$CITY_ID = ?",
            arrayOf(id)
        ) > 0
    }

    @SuppressLint("Recycle")
    override fun getFavoriteCities(): MutableList<City> {
        val cities = mutableListOf<City>()
        val cursor = readableDatabase.query(
            CITY_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                cities.add(City(it))
            }
        }
        return cities
    }

    companion object {
        private var instance: FavoriteCityDAOImpl? = null

        fun getInstance(database: AppDatabase): FavoriteCityDAOImpl =
            instance ?: getInstance(database).also {
                instance = it
            }
    }
}
