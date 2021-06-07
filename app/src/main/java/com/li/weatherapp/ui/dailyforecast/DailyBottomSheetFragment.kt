package com.li.weatherapp.ui.dailyforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.li.weatherapp.R
import com.li.weatherapp.data.model.Daily
import com.li.weatherapp.utils.AirPollutionUtils
import com.li.weatherapp.utils.Constants
import com.li.weatherapp.utils.convertToMilli
import kotlinx.android.synthetic.main.bottom_sheet_detail.*
import java.text.SimpleDateFormat
import java.util.*

class DailyBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Daily>(BUNDLE_DAILY)?.apply {
            val fullDateFormat = SimpleDateFormat(Constants.FULL_DATE_FORMAT, Locale.ENGLISH)
            val timeFormat = SimpleDateFormat(Constants.TIME_FORMAT, Locale.ENGLISH)
            textDateBottomSheet.text = fullDateFormat.format(time.convertToMilli())
            textTemperatureBottomSheet.text = temp.day.toInt().toString()
            textDescriptionBottomSheet.text = weather.first().main
            textRealFeelBottomSheet.text = feelsLike.day.toString()
            textWindBottomSheet.text = windSpeed.toString()
            textWindDegreeBottomSheet.text = windDeg.toString()
            textHumidityBottomSheet.text = humidity.toString()
            textPressureBottomSheet.text = pressure.toString()
            textCloudBottomSheet.text = clouds.toString()
            textUVBottomSheet.text = uvi.toString()
            textSunriseBottomSheet.text = timeFormat.format(sunrise.convertToMilli())
            textSunsetBottomSheet.text = timeFormat.format(sunset.convertToMilli())
            imageDescriptionBottomSheet.setImageResource(AirPollutionUtils.getImageDescription(weather.first().main))
        }
    }

    companion object {
        private const val BUNDLE_DAILY = "BUNDLE_DAILY"
        fun newInstance(item: Daily) = DailyBottomSheetFragment().apply {
            arguments = bundleOf(BUNDLE_DAILY to item)
        }
    }
}
