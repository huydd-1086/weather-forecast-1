package com.li.weatherapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.li.weatherapp.ui.main.MainActivity

abstract class BaseFragment : Fragment() {

    @get: LayoutRes
    protected abstract val layoutResource: Int
    protected open var bottomNavigationViewVisibility = View.VISIBLE

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is MainActivity) {
            val mainActivity = activity as MainActivity
            mainActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutResource, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        setupViews()
        initActions()
    }

    protected abstract fun setupViews()

    protected abstract fun setupData()

    protected abstract fun initActions()
}
