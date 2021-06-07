package com.li.weatherapp.ui.weatherdetail

import android.view.View
import androidx.core.os.bundleOf
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseFragment
import com.li.weatherapp.data.model.CurrentWeather
import com.li.weatherapp.data.model.History
import com.li.weatherapp.data.repository.HistoryRepository
import com.li.weatherapp.data.source.remote.HistoryRemoteDataSource
import com.li.weatherapp.ui.adapters.HistoryAdapter
import com.li.weatherapp.utils.*
import kotlinx.android.synthetic.main.fragment_weather_detail.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherDetailFragment : BaseFragment(), WeatherDetailContract.View {

    override val layoutResource get() = R.layout.fragment_weather_detail
    override var bottomNavigationViewVisibility = View.GONE

    private val adapter = HistoryAdapter()
    private var presenter: WeatherDetailContract.Presenter? = null
    private var lat = ""
    private var lon = ""

    override fun setupViews() {
        buttonBack.isEnabled = false
        progressLoading.visibility = View.VISIBLE
        setupAdapter()
    }

    override fun setupData() {
        getDataFromCurrent()
        presenter = WeatherDetailPresenter(
            this,
            HistoryRepository.getInstance(HistoryRemoteDataSource.getInstance())
        )
        presenter?.getHistory(lat, lon)
    }

    override fun initActions() {
        buttonBack.setOnClickListener {
            fragmentManager?.removeFragment(this)
        }
    }

    override fun showHistory(historyList: List<History>) {
        adapter.updateData(historyList)
        progressLoading.visibility = View.GONE
        buttonBack.isEnabled = true
    }

    override fun showMessage(data: Any) {
        context?.showToast(data.toString())
    }

    private fun getDataFromCurrent() {
        val currentWeather =
            arguments?.getParcelable<CurrentWeather>(BUNDLE_WEATHER_DETAIL) ?: return
        lat = currentWeather.coordinate.lat.toString()
        lon = currentWeather.coordinate.lon.toString()
        textTemperature.text = currentWeather.currentTemp.currentTemp.toInt().toString()
        textDescriptionTitle.text = currentWeather.currentWeather.description.capitalize()
        textRealFeel.text = currentWeather.currentTemp.realFeeling.toInt()
            .withUnit(Constants.DEFAULT_DEGREE)
        textWind.text =
            currentWeather.wind.speed.toInt().withUnit(Constants.DEFAULT_KILOMETER)
        textWindDegree.text =
            currentWeather.wind.degree.toInt().withUnit(Constants.DEFAULT_DEGREE)
        textHumidity.text =
            currentWeather.currentTemp.humidity.toInt().withUnit(Constants.DEFAULT_PERCENT)
        textPressure.text =
            currentWeather.currentTemp.pressure.toInt().withUnit(Constants.DEFAULT_PRESSURE)
        textCloud.text =
            currentWeather.cloud.coverage.toInt().withUnit(Constants.DEFAULT_PERCENT)
        textVisibility.text = currentWeather.visibility.toInt().toString()
        val timeFormat = SimpleDateFormat(Constants.TIME_FORMAT, Locale.ENGLISH)
        textSunrise.text =
            timeFormat.format(currentWeather.sun.sunrise.convertToMilli())
        textSunset.text =
            timeFormat.format(currentWeather.sun.sunset.convertToMilli())
        imageDescription.setImageResource(AirPollutionUtils.getImageDescription(currentWeather.currentWeather.main))
    }

    private fun setupAdapter() {
        recyclerViewHistory.adapter = adapter
    }

    companion object {
        private const val BUNDLE_WEATHER_DETAIL = "BUNDLE_WEATHER_DETAIL"

        fun getInstance(weather: CurrentWeather) = WeatherDetailFragment().apply {
            arguments = bundleOf(BUNDLE_WEATHER_DETAIL to weather)
        }
    }
}
