package com.li.weatherapp.ui.hourly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.li.weatherapp.R
import com.li.weatherapp.data.model.Hourly
import com.li.weatherapp.utils.AirPollutionUtils
import com.li.weatherapp.utils.Constants
import com.li.weatherapp.utils.convertToMilli
import kotlinx.android.synthetic.main.bottom_sheet_detail.*
import java.text.SimpleDateFormat
import java.util.*

class HourlyBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_hourly, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Hourly>(BUNDLE_HOURLY)?.apply {
            val fullDateFormat = SimpleDateFormat(Constants.FULL_DATE_FORMAT, Locale.ENGLISH)
            val timeFormat = SimpleDateFormat(Constants.TIME_FORMAT, Locale.ENGLISH)
            textDateBottomSheet.text =
                "${timeFormat.format(time.convertToMilli())}  ${fullDateFormat.format(time.convertToMilli())}"
            textTemperatureBottomSheet.text = temp.toInt().toString()
            textDescriptionBottomSheet.text = weather.description.capitalize()
            textRealFeelBottomSheet.text = feelsLike.toInt().toString()
            textWindBottomSheet.text = windSpeed.toInt().toString()
            textWindDegreeBottomSheet.text = windDeg.toInt().toString()
            textHumidityBottomSheet.text = humidity.toString()
            textPressureBottomSheet.text = pressure.toString()
            textCloudBottomSheet.text = clouds.toString()
            textUVBottomSheet.text = uvi.toString()
            imageDescriptionBottomSheet.setImageResource(AirPollutionUtils.getImageDescription(weather.main))
        }
    }

    companion object {
        private const val BUNDLE_HOURLY = "BUNDLE_HOURLY"
        fun newInstance(item: Hourly) = HourlyBottomSheetFragment().apply {
            arguments = bundleOf(BUNDLE_HOURLY to item)
        }
    }
}
