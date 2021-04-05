package com.li.weatherapp.ui.warning

import android.content.Intent
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseFragment
import com.li.weatherapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_warning.*

class WarningFragment : BaseFragment() {
    override val layoutResource get() = R.layout.fragment_warning

    override fun setupViews() {
        textWarning.text = activity?.getText(R.string.text_permission)
    }

    override fun setupData() {
    }

    override fun initActions() {
        buttonWarning.setOnClickListener {
            startActivity(activity?.application?.let {
                MainActivity.getIntent(it)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
        }
    }
}
