package com.li.weatherapp.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val items = mutableListOf<T>()

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount() = items.size

    fun updateData(collection: List<T>?) {
        collection?.let {
            items.clear()
            items.addAll(it)
            notifyDataSetChanged()
        }
    }

}
