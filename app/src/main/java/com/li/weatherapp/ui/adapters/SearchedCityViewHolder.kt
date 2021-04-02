package com.li.weatherapp.ui.adapters

import android.view.View
import com.li.weatherapp.base.BaseViewHolder
import com.li.weatherapp.data.model.SearchedCity
import kotlinx.android.synthetic.main.item_searched_city.view.*

class SearchedCityViewHolder(
    private val view: View,
    private var itemClick: (SearchedCity) -> Unit
) : BaseViewHolder<SearchedCity>(view) {
    private var itemCity: SearchedCity? = null

    init {
        view.setOnClickListener {
            itemCity?.let {
                itemClick(it)
            }
        }
    }

    override fun bindData(item: SearchedCity) {
        itemCity = item
        view.run {
            textSearchedCity.text = "${item.name}"
        }
    }
}
