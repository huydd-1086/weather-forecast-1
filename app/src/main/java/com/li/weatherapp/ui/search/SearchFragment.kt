package com.li.weatherapp.ui.search

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseFragment
import com.li.weatherapp.data.model.SearchedCity
import com.li.weatherapp.data.repository.CurrentCityRepository
import com.li.weatherapp.data.repository.SearchedCityRepository
import com.li.weatherapp.data.source.local.CurrentCityLocalDataSource
import com.li.weatherapp.data.source.local.SearchedCityLocalDataSource
import com.li.weatherapp.data.source.local.dao.FavoriteCityDAOImpl
import com.li.weatherapp.data.source.local.db.AppDatabase
import com.li.weatherapp.data.source.remote.SearchedCityRemoteDataSource
import com.li.weatherapp.ui.adapters.RecentCityAdapter
import com.li.weatherapp.ui.adapters.SearchedCityAdapter
import com.li.weatherapp.ui.map.MapFragment
import com.li.weatherapp.utils.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment(), SearchContact.View {

    override val layoutResource get() = R.layout.fragment_search
    override var bottomNavigationViewVisibility = View.GONE

    private val recentCityAdapter = RecentCityAdapter(
        this::recentCityItemClick,
        this::imageFavoriteItemClick
    )
    private val searchedCityAdapter = SearchedCityAdapter(this::searchedCityItemClick)
    private var presenter: SearchContact.Presenter? = null

    override fun setupViews() {
        setupAdapter()
    }

    override fun setupData() {
        context?.let {
            presenter = SearchPresenter(
                this,
                SearchedCityRepository.getInstance(
                    SearchedCityRemoteDataSource.getInstance(),
                    SearchedCityLocalDataSource.getInstance(
                        FavoriteCityDAOImpl.getInstance(
                            AppDatabase.getInstance(it)
                        )
                    )
                ),
                CurrentCityRepository.getInstance(
                    CurrentCityLocalDataSource.getInstance(
                        SharePreferenceHelper.getInstance(it)
                    )
                )
            )
        }
        presenter?.start()
    }

    override fun initActions() {
        buttonSearch.setOnClickListener {
            val cityName = textSearch.text.toString()
            if (cityName == Constants.DEFAULT_STRING) {
                showToastText(R.string.text_fill_city_name)
            } else {
                closeKeyboard()
                presenter?.getSearchedCities(cityName)
            }
        }
        buttonBack.setOnClickListener {
            fragmentManager?.removeFragment(this)
        }
        buttonMap.setOnClickListener {
            fragmentManager?.replaceFragment(R.id.frameMain, MapFragment())
        }
    }

    override fun showSearchedCities(cities: List<SearchedCity>) {
        if (cities.isEmpty()) {
            showToastText(R.string.text_cannot_find_city_name)
        } else {
            searchedCityAdapter.updateData(cities)
            recyclerRecent.visibility = View.GONE
            textRecent.visibility = View.GONE
        }
    }

    override fun showRecentCities(cities: List<SearchedCity>) {
        if (cities.isEmpty()) {
            textRecent.visibility = View.GONE
        }
        recentCityAdapter.updateData(cities)
    }

    override fun showInfoInsertRecentCity(isInsert: Boolean) {
    }

    override fun showInfoInsertFavoriteCity(isInsert: Boolean) {
        showToastText(R.string.text_add_to_favorite)
        recentCityAdapter.notifyDataSetChanged()
    }

    override fun showDeleteFavoriteInfo(isDelete: Boolean) {
        if (isDelete) {
            showToastText(R.string.text_delete_favorite)
            recentCityAdapter.notifyDataSetChanged()
        } else {
            showToastText(R.string.text_delete_fail)
        }
    }

    override fun showMessage(data: Any) {
        context?.showToast(data.toString())
    }

    private fun closeKeyboard() {
        activity?.currentFocus?.let {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, FLAG)
        }
    }

    private fun setupAdapter() {
        recyclerSearchResult.adapter = searchedCityAdapter
        recyclerRecent.adapter = recentCityAdapter
    }

    private fun searchedCityItemClick(item: SearchedCity) {
        presenter?.apply {
            addCityToRecentCities(item)
            setLongitude(item.longitude)
            setLatitude(item.latitude)
        }
        fragmentManager?.removeFragment(this)
    }

    private fun recentCityItemClick(item: SearchedCity) {
        presenter?.apply {
            setLongitude(item.longitude)
            setLatitude(item.latitude)
        }
        fragmentManager?.removeFragment(this)
    }

    private fun imageFavoriteItemClick(item: SearchedCity) {
        if (checkFavoriteValue(item.isFavorite)) {
            presenter?.deleteFavoriteCity(getUpdateCity(Constants.NON_FAVORITE_VALUE, item))
        } else {
            presenter?.addCityToFavoriteCities(getUpdateCity(Constants.IS_FAVORITE_VALUE, item))
        }
    }

    private fun showToastText(id: Int) {
        context?.run {
            showToast(resources.getString(id))
        }
    }

    private fun getUpdateCity(value: Int, item: SearchedCity): SearchedCity {
        item.isFavorite = value
        return item
    }

    private fun checkFavoriteValue(check: Int) = check > 0

    companion object {
        const val FLAG = 0
    }
}
