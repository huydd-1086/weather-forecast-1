package com.li.weatherapp.ui.map

import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseFragment
import com.li.weatherapp.data.repository.CurrentCityRepository
import com.li.weatherapp.data.source.local.CurrentCityLocalDataSource
import com.li.weatherapp.utils.Constants
import com.li.weatherapp.utils.SharePreferenceHelper
import com.li.weatherapp.utils.removeFragment
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : BaseFragment() {

    override val layoutResource get() = R.layout.fragment_map
    override var bottomNavigationViewVisibility = View.GONE

    private var lon = ""
    private var lat = ""
    private var presenter: MapContract.Presenter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.fragmentMap) as? SupportMapFragment
        supportMapFragment?.getMapAsync { map ->
            map?.setOnMapClickListener {
                buttonCheckMap.visibility = View.VISIBLE
                val markerOptions = MarkerOptions()
                markerOptions.position(it)
                markerOptions.title(it.latitude.toString() + " : " + it.longitude.toString())
                map.apply {
                    clear()
                    animateCamera(CameraUpdateFactory.newLatLngZoom(it, Constants.MAP_ZOOM_COEFFICIENT))
                    addMarker(markerOptions)
                }
                lat = it.latitude.toString()
                lon = it.longitude.toString()
            }
        }
    }

    override fun setupViews() {
    }

    override fun setupData() {
        context?.let {
            presenter = MapPresenter(
                CurrentCityRepository.getInstance(
                    CurrentCityLocalDataSource.getInstance(
                        SharePreferenceHelper.getInstance(it)
                    )
                )
            )
        }
    }

    override fun initActions() {
        buttonBackMap.setOnClickListener {
            fragmentManager?.removeFragment(this)
        }
        buttonCheckMap.setOnClickListener {
            presenter?.setLatitude(lat)
            presenter?.setLongitude(lon)
            val searchFragment = fragmentManager?.findFragmentByTag(MapFragment().javaClass.name)
            fragmentManager?.let {
                if (searchFragment != null) {
                    it.popBackStack()
                    it.removeFragment(searchFragment)
                }
                it.removeFragment(this)
            }
        }
    }
}
