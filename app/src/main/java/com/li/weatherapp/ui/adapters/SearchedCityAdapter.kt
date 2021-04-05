package com.li.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseAdapter
import com.li.weatherapp.base.BaseViewHolder
import com.li.weatherapp.data.model.SearchedCity

class SearchedCityAdapter(private var itemClick: (SearchedCity) -> Unit) :
    BaseAdapter<SearchedCity>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SearchedCity> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_searched_city, parent, false)
        return SearchedCityViewHolder(view, itemClick)
    }
}
