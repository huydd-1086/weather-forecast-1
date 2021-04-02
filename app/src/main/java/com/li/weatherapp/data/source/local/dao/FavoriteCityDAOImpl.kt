package com.li.weatherapp.data.source.local.dao

import com.li.weatherapp.data.model.SearchedCity
import com.li.weatherapp.data.source.local.db.AppDatabase
import com.li.weatherapp.utils.Constants

class FavoriteCityDAOImpl private constructor(database: AppDatabase) : FavoriteCityDAO {

    private val writableDatabase = database.writableDatabase
    private val readableDatabase = database.readableDatabase

    override fun insertFavoriteCity(city: SearchedCity): Boolean {
        return writableDatabase.insert(
            SearchedCity.CITY_TABLE_NAME,
            null,
            city.getContentValues()
        ) > 0
    }

    override fun updateFavoriteCity(city: SearchedCity): Boolean {
        return writableDatabase.update(
            SearchedCity.CITY_TABLE_NAME,
            city.getContentValues(),
            "${SearchedCity.CITY_NAME} = ?",
            arrayOf(city.name)
        ) > 0
    }

    override fun getFavoriteCities(): List<SearchedCity> {
        val cities = mutableListOf<SearchedCity>()
        val query =
            "SELECT * FROM ${SearchedCity.CITY_TABLE_NAME} WHERE ${SearchedCity.CITY_IS_FAVORITE} = ${Constants.IS_FAVORITE_VALUE}"
        val cursor = readableDatabase.rawQuery(query, null)
        cursor.use {
            while (it.moveToNext()) {
                cities.add(SearchedCity(it))
            }
        }
        return cities
    }

    override fun getRecentCities(): List<SearchedCity> {
        val cities = mutableListOf<SearchedCity>()
        val cursor = readableDatabase.query(
            SearchedCity.CITY_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                cities.add(SearchedCity(it))
            }
        }
        return cities
    }

    companion object {
        private var instance: FavoriteCityDAOImpl? = null

        fun getInstance(database: AppDatabase): FavoriteCityDAOImpl =
            instance ?: synchronized(this) {
                instance ?: FavoriteCityDAOImpl(database).also {
                    instance = it
                }
            }
    }
}
