package com.li.weatherapp.ui.airquality

import android.content.Context
import android.content.Intent
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseActivity

class AirQualityActivity : BaseActivity() {

    override val layoutResource get() = R.layout.activity_air_quality

    override fun initViews() {
    }

    override fun initData() {
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, AirQualityActivity::class.java)
    }
}
