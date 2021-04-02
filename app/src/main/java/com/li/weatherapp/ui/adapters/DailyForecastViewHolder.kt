package com.li.weatherapp.ui.adapters

import android.view.View
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseViewHolder
import com.li.weatherapp.data.model.Daily
import com.li.weatherapp.utils.Constants
import com.li.weatherapp.utils.convertToMilli
import kotlinx.android.synthetic.main.item_daily_forecast.view.*
import java.text.SimpleDateFormat
import java.util.*

class DailyForecastViewHolder(
    private val view: View,
    private val max: Int,
    private val min: Int,
    private var itemClick: (Daily) -> Unit
) : BaseViewHolder<Daily>(view) {

    private var itemDaily: Daily? = null

    init {
        view.setOnClickListener {
            itemDaily?.let {
                itemClick(it)
            }
        }
    }

    override fun bindData(item: Daily) {
        itemDaily = item
        view.run {
            val simpleDateFormat = SimpleDateFormat(Constants.FULL_DATE_FORMAT, Locale.ENGLISH)
            val simpleDayOfWeekFormat = SimpleDateFormat(Constants.DAY_OF_WEEK, Locale.ENGLISH)
            textDate.text = simpleDateFormat.format(item.time.convertToMilli())
            textDayOfWeek.text = simpleDayOfWeekFormat.format(item.time.convertToMilli())
            textTemperatureMax.text = item.temp.max.toInt().toString()
            textTemperatureMin.text = item.temp.min.toInt().toString()
            textHumidity.text = item.humidity.toString()
            viewTemperatureTop.layoutParams.height =
                resources.getDimension(R.dimen.dp_20)
                    .toInt() * (Constants.TEMPERATURE_COEFFICIENT + max - item.temp.max.toInt())
            viewTemperatureBottom.layoutParams.height =
                resources.getDimension(R.dimen.dp_20)
                    .toInt() * (item.temp.min.toInt() - min + Constants.TEMPERATURE_COEFFICIENT)
        }
    }
}
