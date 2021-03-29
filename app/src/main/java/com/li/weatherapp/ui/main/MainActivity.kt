package com.li.weatherapp.ui.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseActivity
import com.li.weatherapp.base.BaseFragment
import com.li.weatherapp.base.BaseView
import com.li.weatherapp.data.repository.CurrentCityRepository
import com.li.weatherapp.data.source.local.CurrentCityLocalDataSource
import com.li.weatherapp.ui.warning.WarningFragment
import com.li.weatherapp.ui.currentweather.CurrentWeatherFragment
import com.li.weatherapp.ui.dailyforecast.DailyForecastFragment
import com.li.weatherapp.ui.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.li.weatherapp.utils.SharePreferenceHelper
import com.li.weatherapp.utils.showToast
import java.util.*

class MainActivity : BaseActivity(), LocationListener, BaseView {

    private val currentWeatherFragment = CurrentWeatherFragment()
    private val hourlyForecastFragment = CurrentWeatherFragment()
    private val dailyForecastFragment = DailyForecastFragment()
    private val favoriteCitiesFragment = CurrentWeatherFragment()
    private val newsFragment = NewsFragment()
    private var locationManager: LocationManager? = null
    private var presenter: CurrentCityContact.Presenter? = null

    private val onBottomNavigationItemSelect =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuCurrentWeather -> showFragment(currentWeatherFragment)
                R.id.menuHourly -> showFragment(hourlyForecastFragment)
                R.id.menuDaily -> showFragment(dailyForecastFragment)
                R.id.menuFavorite -> showFragment(favoriteCitiesFragment)
                R.id.menuNews -> showFragment(newsFragment)
            }
            true
        }

    override val layoutResource get() = R.layout.activity_main

    override fun initViews() {
    }

    override fun initActions() {
        presenter = CurrentCityPresenter(
            CurrentCityRepository.getInstance(
                CurrentCityLocalDataSource.getInstance(
                    SharePreferenceHelper.getInstance(this)
                )
            )
        )
    }

    override fun initData() {
        getCurrentLocation()
    }

    override fun showMessage(data: Any) {
        this.showToast(data.toString())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation()
        } else {
            showFragment(WarningFragment())
        }
    }

    override fun onLocationChanged(location: Location) {
        val geo = Geocoder(this, Locale.getDefault())
        val addresses: List<Address>
        addresses = geo.getFromLocation(
            location.latitude,
            location.longitude, MAX_RESULT
        )
        if (addresses.isNotEmpty()) {
            val cityName = addresses.first().locality
            presenter?.apply {
                setCityName(cityName)
                setLatitude(location.latitude)
                setLongitude(location.longitude)
            }
            setUpNavigation()
        } else {
            showMessage(this.resources.getString(R.string.text_warning_location))
        }
    }

    private fun showFragment(fragment: BaseFragment) =
        supportFragmentManager.apply {
            popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            beginTransaction()
                .replace(R.id.frameMain, fragment)
                .commit()
        }

    private fun getCurrentLocation() {
        locationManager =
            applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_CODE
            )
        } else {
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME,
                MIN_DISTANCE,
                this
            )
        }
    }

    private fun setUpNavigation() {
        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener(onBottomNavigationItemSelect)
            selectedItemId = R.id.menuCurrentWeather
        }
    }

    companion object {
        const val REQUEST_CODE = 1
        const val MIN_TIME = 5000L
        const val MIN_DISTANCE = 5f
        const val MAX_RESULT = 1

        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
