package com.li.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseAdapter
import com.li.weatherapp.base.BaseViewHolder
import com.li.weatherapp.data.model.History

class HistoryAdapter : BaseAdapter<History>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<History> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }
}
