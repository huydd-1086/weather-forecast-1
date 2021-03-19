package com.li.weatherapp.ui.weatherdetail

import android.content.Context
import android.content.Intent
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseActivity
import com.li.weatherapp.ui.main.MainActivity

class WeatherDetailActivity : BaseActivity() {

    override val layoutResource get() = R.layout.activity_weather_detail

    override fun initViews() {
    }

    override fun initData() {
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, WeatherDetailActivity::class.java)
    }
}
