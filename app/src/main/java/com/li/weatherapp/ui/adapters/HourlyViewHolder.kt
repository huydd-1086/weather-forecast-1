package com.li.weatherapp.ui.adapters

import android.view.View
import com.li.weatherapp.base.BaseViewHolder
import com.li.weatherapp.data.model.Hourly
import com.li.weatherapp.utils.AirPollutionUtils
import kotlinx.android.synthetic.main.item_hourly.view.*
import com.li.weatherapp.utils.Constants
import com.li.weatherapp.utils.convertToMilli
import java.text.SimpleDateFormat
import java.util.*

class HourlyViewHolder(
    private val view: View,
    private var itemClick: (Hourly) -> Unit
) : BaseViewHolder<Hourly>(view) {
    private var itemHourly: Hourly? = null

    init {
        view.setOnClickListener {
            itemHourly?.let { itemClick(it) }
        }
    }

    override fun bindData(item: Hourly) {
        itemHourly = item
        val fullDateFormat = SimpleDateFormat(Constants.DAY_OF_WEEK, Locale.ENGLISH)
        val timeFormat = SimpleDateFormat(Constants.TIME_FORMAT, Locale.ENGLISH)
        view.run {
            textDayTime.text = fullDateFormat.format(item.time.convertToMilli())
            textHourly.text = timeFormat.format(item.time.convertToMilli())
            textTemperature.text = item.temp.toInt().toString()
            textHumidity.text = item.humidity.toString()
            imageWeather.setImageResource(AirPollutionUtils.getImageDescription(item.weather.main))
        }
    }
}
