package com.li.weatherapp.ui.currentweather

import android.view.View
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseFragment
import com.li.weatherapp.data.model.AQI
import com.li.weatherapp.data.model.CurrentWeather
import com.li.weatherapp.data.repository.AQIRepository
import com.li.weatherapp.data.repository.CurrentCityRepository
import com.li.weatherapp.data.repository.CurrentWeatherRepository
import com.li.weatherapp.data.source.local.CurrentCityLocalDataSource
import com.li.weatherapp.data.source.remote.AQIRemoteDataSource
import com.li.weatherapp.data.source.remote.CurrentWeatherRemoteDataSource
import com.li.weatherapp.ui.airquality.AirQualityFragment
import com.li.weatherapp.ui.search.SearchFragment
import com.li.weatherapp.ui.setting.SettingFragment
import com.li.weatherapp.ui.weatherdetail.WeatherDetailFragment
import com.li.weatherapp.utils.*
import kotlinx.android.synthetic.main.fragment_current_weather.*
import kotlinx.android.synthetic.main.fragment_weather_detail.*
import kotlinx.android.synthetic.main.layout_air_quality.*
import kotlinx.android.synthetic.main.layout_air_quality.progressAirQuality
import kotlinx.android.synthetic.main.layout_temperature.*
import kotlinx.android.synthetic.main.layout_weather_detail.*
import kotlinx.android.synthetic.main.layout_weather_detail.textHumidity
import kotlinx.android.synthetic.main.layout_weather_detail.textWind
import kotlinx.android.synthetic.main.layout_weather_detail.textWindDegree
import java.text.SimpleDateFormat
import java.util.*

class CurrentWeatherFragment : BaseFragment(), CurrentWeatherForecastContact.View {

    override val layoutResource get() = R.layout.fragment_current_weather

    private var presenter: CurrentWeatherForecastContact.Presenter? = null
    private var cityName = ""
    private var aqiDegree: AQI? = null
    private var weather: CurrentWeather? = null

    override fun setupViews() {
        showCurrentTime()
        progressLoadingCurrent.visibility = View.VISIBLE
    }

    override fun setupData() {
        presenter = context?.let {
            CurrentWeatherPresenter(
                this,
                CurrentWeatherRepository.getInstance(CurrentWeatherRemoteDataSource.getInstance()),
                AQIRepository.getInstance(AQIRemoteDataSource.getInstance()),
                CurrentCityRepository.getInstance(
                    CurrentCityLocalDataSource.getInstance(
                        SharePreferenceHelper.getInstance(it)
                    )
                )
            )
        }
        presenter?.getCurrentLocation()
    }

    override fun initActions() {
        buttonSettingCurrentWeather.setOnClickListener {
            fragmentManager?.replaceFragment(
                R.id.frameMain,
                SettingFragment()
            )
        }
        buttonAirInformation.setOnClickListener {
            aqiDegree?.let {
                fragmentManager?.replaceFragment(
                    R.id.frameMain,
                    AirQualityFragment.getInstance(it)
                )
            }
        }
        buttonSeeMore.setOnClickListener {
            weather?.let {
                fragmentManager?.replaceFragment(
                    R.id.frameMain,
                    WeatherDetailFragment.getInstance(it)
                )
            }
        }
        textTitleLocation.setOnClickListener {
            fragmentManager?.replaceFragment(
                R.id.frameMain,
                SearchFragment()
            )
        }
    }

    override fun showCurrentWeatherForecast(weather: CurrentWeather) {
        progressLoadingCurrent.visibility = View.GONE
        this.weather = weather
        textCurrentTemper.text = weather.currentTemp.currentTemp.toInt().toString()
        textDescription.text = weather.currentWeather.description.capitalize()
        textTemperatureData.text = formatString(
            weather.currentTemp.min.toInt().toString(),
            weather.currentTemp.max.toInt().toString()
        )
        textWind.text = weather.wind.speed.toInt().withUnit(Constants.DEFAULT_KILOMETER)
        textWindDegree.text = weather.wind.degree.toInt().withUnit(Constants.DEFAULT_DEGREE)
        textHumidity.text = weather.currentTemp.humidity.toInt().withUnit(Constants.DEFAULT_PERCENT)
        if (weather.cityName == "") {
            this.cityName = context?.resources?.getString(R.string.text_unknown_place).toString()
        } else {
            this.cityName = weather.cityName
        }
        textTitleLocation.text = this.cityName
    }

    override fun showAQIForecast(airQuality: AQI) {
        aqiDegree = airQuality
        textAqi.text = airQuality.aqi.toString()
        progressAirQuality.progress = airQuality.aqi
        context?.let {
            textMeasure.text = AirPollutionUtils.getAirPollutionTitle(it, airQuality.aqi)
            textAirDescription.text =
                AirPollutionUtils.getAirPollutionDescription(it, airQuality.aqi)
        }
    }

    override fun updateLocation(lat: String, lon: String) {
        presenter?.getCurrentWeatherForecast(lat, lon)
        presenter?.getAQI(lat, lon)
        presenter?.setCityName(cityName)
    }

    override fun showMessage(data: Any) {
    }

    private fun formatString(min: String, max: String) =
        "$min ${Constants.DEFAULT_CELSIUS} - $max ${Constants.DEFAULT_CELSIUS}"

    private fun showCurrentTime() {
        val currentDate =
            SimpleDateFormat(Constants.FULL_DATE_FORMAT, Locale.getDefault()).format(Date())
        textCurrentTime.text = currentDate
    }
}
