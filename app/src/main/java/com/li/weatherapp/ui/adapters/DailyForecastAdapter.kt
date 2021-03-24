package com.li.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseAdapter
import com.li.weatherapp.base.BaseViewHolder
import com.li.weatherapp.data.model.Daily

class DailyForecastAdapter(private var itemClick: (Daily) -> Unit) : BaseAdapter<Daily>() {

    var max = 0
    var min = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Daily> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_daily_forecast, parent, false)
        return DailyForecastViewHolder(view, max, min, itemClick)
    }
}
