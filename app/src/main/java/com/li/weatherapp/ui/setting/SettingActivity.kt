package com.li.weatherapp.ui.setting

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseActivity
import com.li.weatherapp.utils.showToast
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() {

    override val layoutResource get() = R.layout.activity_setting

    override fun initViews() {
        setupNotificationSwitch()
        setupLanguageSpinner()
        buttonBackSetting.setOnClickListener { finish() }
        textAboutClick()
    }

    override fun initData() {
    }

    private fun setupNotificationSwitch() {
        switchNotification.setOnCheckedChangeListener { _, _ ->
            if (switchNotification.isChecked) {
                showToast("Checked")
            } else {
                showToast("Unchecked")
            }
        }
    }

    private fun setupLanguageSpinner() {
        val languageList = listOf(
            resources.getString(R.string.text_language_english),
            resources.getString(R.string.text_language_vietnamese)
        )
        val arrayAdapter =
            ArrayAdapter(this, R.layout.item_spinner_language, languageList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLanguage.apply {
            gravity = Gravity.END
            adapter = arrayAdapter
            onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
    }

    private fun textAboutClick() {
        textAbout.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.AboutDialogTheme)
            builder.apply {
                setTitle(resources.getString(R.string.title_about))
                setMessage(resources.getString(R.string.text_about_message))
                setNegativeButton(resources.getString(R.string.text_ok)) { dialog, _ ->
                    dialog.cancel()
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, SettingActivity::class.java)
    }
}
