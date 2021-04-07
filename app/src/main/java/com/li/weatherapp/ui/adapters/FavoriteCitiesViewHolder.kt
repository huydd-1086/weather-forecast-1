package com.li.weatherapp.ui.adapters

import android.view.View
import com.li.weatherapp.base.BaseViewHolder
import com.li.weatherapp.data.model.CurrentWeather
import com.li.weatherapp.utils.AirPollutionUtils
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteCitiesViewHolder(private val view: View) : BaseViewHolder<CurrentWeather>(view) {

    override fun bindData(item: CurrentWeather) {
        view.run {
            textFavoriteCityName.text = item.cityName
            textTemperFavorite.text = item.currentTemp.currentTemp.toInt().toString()
            textWindSpeedFavorite.text = item.wind.speed.toInt().toString()
            textHumidityFavorite.text = item.currentTemp.humidity.toInt().toString()
            textDescriptionFavorite.text = item.currentWeather.description.capitalize()
            imageWeatherFavorite.setImageResource(AirPollutionUtils.getImageDescription(item.currentWeather.main))
        }
    }

}
