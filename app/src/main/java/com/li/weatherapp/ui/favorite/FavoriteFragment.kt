package com.li.weatherapp.ui.favorite

import com.li.weatherapp.R
import com.li.weatherapp.base.BaseFragment
import com.li.weatherapp.data.model.CurrentWeather
import com.li.weatherapp.data.repository.CurrentWeatherRepository
import com.li.weatherapp.data.repository.SearchedCityRepository
import com.li.weatherapp.data.source.local.SearchedCityLocalDataSource
import com.li.weatherapp.data.source.local.dao.FavoriteCityDAOImpl
import com.li.weatherapp.data.source.local.db.AppDatabase
import com.li.weatherapp.data.source.remote.CurrentWeatherRemoteDataSource
import com.li.weatherapp.data.source.remote.SearchedCityRemoteDataSource
import com.li.weatherapp.ui.adapters.FavoriteCitiesAdapter
import com.li.weatherapp.ui.setting.SettingFragment
import com.li.weatherapp.utils.replaceFragment
import com.li.weatherapp.utils.showToast
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseFragment(), FavoriteContact.View {

    override val layoutResource get() = R.layout.fragment_favorite

    private val favoriteAdapter = FavoriteCitiesAdapter()
    private val weatherCities = mutableListOf<CurrentWeather>()
    private var presenter: FavoriteContact.Presenter? = null

    override fun setupViews() {
        recyclerViewFavorite.adapter = favoriteAdapter
    }

    override fun setupData() {
        context?.let {
            presenter = FavoritePresenter(
                this,
                SearchedCityRepository.getInstance(
                    SearchedCityRemoteDataSource.getInstance(),
                    SearchedCityLocalDataSource.getInstance(
                        FavoriteCityDAOImpl.getInstance(
                            AppDatabase.getInstance(it)
                        )
                    )
                ),
                CurrentWeatherRepository.getInstance(CurrentWeatherRemoteDataSource.getInstance())
            )
        }
        weatherCities.clear()
        presenter?.getFavoriteCities()
    }

    override fun initActions() {
        buttonSettingFavorite.setOnClickListener {
            fragmentManager?.replaceFragment(R.id.frameMain, SettingFragment())
        }
    }

    override fun showWeatherForecast(weather: CurrentWeather) {
        weatherCities.add(weather)
        favoriteAdapter.updateData(weatherCities)
    }

    override fun showMessage(data: Any) {
        context?.showToast(data.toString())
    }
}
