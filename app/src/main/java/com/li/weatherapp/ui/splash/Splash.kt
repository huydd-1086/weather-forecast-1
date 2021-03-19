package com.li.weatherapp.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.li.weatherapp.ui.main.MainActivity

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(MainActivity.getIntent(this))
        finish()
    }
}
