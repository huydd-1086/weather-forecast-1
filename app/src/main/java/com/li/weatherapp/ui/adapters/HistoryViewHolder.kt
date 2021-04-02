package com.li.weatherapp.ui.adapters

import android.view.View
import com.li.weatherapp.base.BaseViewHolder
import com.li.weatherapp.data.model.History
import com.li.weatherapp.utils.Constants
import com.li.weatherapp.utils.convertToMilli
import com.li.weatherapp.utils.withUnit
import kotlinx.android.synthetic.main.item_history.view.*
import java.text.SimpleDateFormat
import java.util.*

class HistoryViewHolder(private val view: View) : BaseViewHolder<History>(view) {

    override fun bindData(item: History) {
        view.run {
            val shortDateFormat = SimpleDateFormat(Constants.SHORT_DATE_FORMAT, Locale.ENGLISH)
            textDate.text = shortDateFormat.format(item.time.convertToMilli())
            textTemperature.text = item.temp.toInt().withUnit(Constants.DEFAULT_CELSIUS)
            textDescription.text = item.weather.first().main
            textHumidity.text = item.humidity.toInt().withUnit(Constants.DEFAULT_PERCENT)
            textWind.text = item.wind.toInt().withUnit(Constants.DEFAULT_KILOMETER)
        }
    }
}
