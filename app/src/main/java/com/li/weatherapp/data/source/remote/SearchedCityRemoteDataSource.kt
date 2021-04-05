package com.li.weatherapp.data.source.remote

import com.li.weatherapp.data.model.SearchedCity
import com.li.weatherapp.data.source.SearchedCityDataSource
import com.li.weatherapp.data.source.remote.api.APIQueries
import com.li.weatherapp.data.source.remote.utils.RemoteAsyncTask
import com.li.weatherapp.data.source.remote.utils.makeNetworkCall
import com.li.weatherapp.data.source.utils.OnDataLoadCallback
import com.li.weatherapp.utils.mapToListObject
import org.json.JSONObject

class SearchedCityRemoteDataSource private constructor() : SearchedCityDataSource.Remote {

    override fun getCities(cityName: String, callback: OnDataLoadCallback<List<SearchedCity>>) {
        RemoteAsyncTask(callback) {
            getCities(cityName)
        }.execute()
    }

    private fun getCities(cityName: String): List<SearchedCity> {
        val jsonString = makeNetworkCall(
            APIQueries.queryCities(cityName)
        )
        return JSONObject(jsonString)
            .getJSONArray(SearchedCity.SEARCHED_CITY)
            .mapToListObject(::SearchedCity)
    }

    companion object {
        private var instance: SearchedCityRemoteDataSource? = null
        fun getInstance() = instance ?: SearchedCityRemoteDataSource().also { instance = it }
    }
}
