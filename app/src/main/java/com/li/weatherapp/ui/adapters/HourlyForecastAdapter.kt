package com.li.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseAdapter
import com.li.weatherapp.base.BaseViewHolder
import com.li.weatherapp.data.model.Hourly

class HourlyForecastAdapter(private var itemClick: (Hourly) -> Unit) : BaseAdapter<Hourly>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Hourly> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hourly, parent, false)
        return HourlyViewHolder(view, itemClick)
    }
}
