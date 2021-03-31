package com.li.weatherapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.li.weatherapp.R
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

    @get: LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        applyTheme()
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initData()
        initViews()
        initActions()
    }

    protected abstract fun initViews()

    protected abstract fun initData()

    protected abstract fun initActions()

    private fun applyTheme() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        if (hour in 6..18) {
            setTheme(R.style.WeatherAppLight)
            window.decorView.setBackgroundResource(R.drawable.bg_bright)
        } else {
            setTheme(R.style.WeatherAppDark)
            window.decorView.setBackgroundResource(R.drawable.bg_night)
        }
    }
}
