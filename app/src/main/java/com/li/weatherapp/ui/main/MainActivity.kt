package com.li.weatherapp.ui.main

import android.content.Context
import android.content.Intent
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseActivity

class MainActivity : BaseActivity() {
    override val layoutResource get() = R.layout.activity_main

    override fun initViews() {

    }

    override fun initActions() {

    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
