package com.li.weatherapp.ui.hourly

import com.li.weatherapp.R
import com.li.weatherapp.base.BaseFragment
import com.li.weatherapp.data.model.Hourly
import com.li.weatherapp.data.repository.CurrentCityRepository
import com.li.weatherapp.data.repository.HourlyRepository
import com.li.weatherapp.data.source.local.CurrentCityLocalDataSource
import com.li.weatherapp.data.source.remote.HourlyRemoteDataSource
import com.li.weatherapp.ui.adapters.HourlyForecastAdapter
import com.li.weatherapp.ui.setting.SettingFragment
import com.li.weatherapp.utils.SharePreferenceHelper
import com.li.weatherapp.utils.replaceFragment
import com.li.weatherapp.utils.showToast
import kotlinx.android.synthetic.main.fragment_hourly_forecast.*

class HourlyForecastFragment : BaseFragment(), HourlyForeCastContract.View {

    override val layoutResource get() = R.layout.fragment_hourly_forecast

    private val adapter = HourlyForecastAdapter(this::hourlyForecastItemClick)
    private var presenter: HourlyForeCastContract.Presenter? = null

    override fun setupViews() {
        setupAdapter()
    }

    override fun setupData() {
        context?.let {
            presenter = HourlyForecastPresenter(
                this,
                HourlyRepository.getInstance(HourlyRemoteDataSource.getInstance()),
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
        swipeRefreshHourly.setOnRefreshListener {
            swipeRefreshHourly.isRefreshing = false
            presenter?.getCurrentLocation()
        }
        buttonSettingHourly.setOnClickListener {
            fragmentManager?.replaceFragment(
                R.id.frameMain,
                SettingFragment()
            )
        }
    }

    override fun updateLocation(lat: String, lon: String) {
        presenter?.getHourlyForecast(lat, lon)
    }

    override fun showHourlyForecast(hourlyForecastList: List<Hourly>) {
        adapter.updateData(hourlyForecastList)
    }

    override fun showMessage(data: Any) {
        context?.showToast(data.toString())
    }

    private fun setupAdapter() {
        recyclerViewHourly.adapter = adapter
    }

    private fun hourlyForecastItemClick(item: Hourly) {
        val hourlyBottomSheetFragment = HourlyBottomSheetFragment.newInstance(item)
        hourlyBottomSheetFragment.show(childFragmentManager, TAG)
    }

    companion object {
        const val TAG = "HourlyBottomSheet"
    }
}
