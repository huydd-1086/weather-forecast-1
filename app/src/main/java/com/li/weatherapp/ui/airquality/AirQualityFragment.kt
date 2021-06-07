package com.li.weatherapp.ui.airquality

import android.view.View
import androidx.core.os.bundleOf
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseFragment
import com.li.weatherapp.data.model.AQI
import com.li.weatherapp.utils.AirPollutionUtils
import com.li.weatherapp.utils.removeFragment
import kotlinx.android.synthetic.main.fragment_air_quality.*
import kotlinx.android.synthetic.main.layout_air_quality_info.*

class AirQualityFragment : BaseFragment() {

    override val layoutResource get() = R.layout.fragment_air_quality
    override var bottomNavigationViewVisibility = View.GONE

    override fun setupViews() {
    }

    override fun setupData() {
        getInputData()
    }

    override fun initActions() {
        buttonBackAirQuality.setOnClickListener {
            fragmentManager?.removeFragment(this)
        }
    }

    private fun getInputData() {
        arguments?.getParcelable<AQI>(BUNDLE_AIR_QUALITY)?.apply {
            progressAirQuality.progress = aqi
            textAqiDegree.text = aqi.toString()
            textCOInfo.text = airComponents.CODegree.toInt().toString()
            textNO2Info.text = airComponents.NO2Degree.toInt().toString()
            textSO2Info.text = airComponents.SO2Degree.toInt().toString()
            textPM10Info.text = airComponents.PM10Degree.toInt().toString()
            context?.let {
                val color = AirPollutionUtils.getAirPollutionColor(it, aqi)
                textAirQuality.text =
                    AirPollutionUtils.getAirPollutionTitle(it, aqi)
                textAirQualityDescription.text =
                    AirPollutionUtils.getAirPollutionDescription(it, aqi)
                viewAirQuality.setBackgroundColor(color)
            }
        }
    }

    companion object {
        private const val BUNDLE_AIR_QUALITY = "BUNDLE_AIR_QUALITY"

        fun getInstance(aqiDegree: AQI) = AirQualityFragment().apply {
            arguments = bundleOf(BUNDLE_AIR_QUALITY to aqiDegree)
        }
    }
}
