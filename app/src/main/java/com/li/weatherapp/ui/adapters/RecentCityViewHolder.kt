package com.li.weatherapp.ui.adapters

import android.view.View
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseViewHolder
import com.li.weatherapp.data.model.SearchedCity
import kotlinx.android.synthetic.main.item_recent.view.*

class RecentCityViewHolder(
    private val view: View,
    private var itemClick: (SearchedCity) -> Unit,
    private var imageClick: (SearchedCity) -> Unit
) : BaseViewHolder<SearchedCity>(view) {
    private var itemCity: SearchedCity? = null

    init {
        view.setOnClickListener {
            itemCity?.let { itemClick(it) }
        }
    }

    override fun bindData(item: SearchedCity) {
        itemCity = item
        view.run {
            textRecentCity.text = item.name
            if (item.isFavorite == 1) {
                imageFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                imageFavorite.setImageResource(R.drawable.ic_non_favorite)
            }
            imageFavorite.setOnClickListener { imageClick(item) }
        }
    }
}
