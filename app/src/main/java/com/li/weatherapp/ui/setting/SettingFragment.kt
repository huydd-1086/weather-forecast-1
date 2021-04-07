package com.li.weatherapp.ui.setting

import android.app.AlertDialog
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.li.weatherapp.R
import com.li.weatherapp.base.BaseFragment
import com.li.weatherapp.data.repository.CurrentCityRepository
import com.li.weatherapp.data.repository.LanguageRepository
import com.li.weatherapp.data.source.local.CurrentCityLocalDataSource
import com.li.weatherapp.data.source.local.LanguageLocalDataSource
import com.li.weatherapp.ui.main.MainActivity
import com.li.weatherapp.utils.NotificationUtils
import com.li.weatherapp.utils.SharePreferenceHelper
import com.li.weatherapp.utils.removeFragment
import com.li.weatherapp.utils.showToast
import kotlinx.android.synthetic.main.fragment_setting.*
import java.util.*

@Suppress("DEPRECATION")
class SettingFragment : BaseFragment(), SettingContract.View {

    override val layoutResource get() = R.layout.fragment_setting
    override var bottomNavigationViewVisibility = View.GONE

    private var presenter: SettingContract.Presenter? = null

    override fun setupViews() {
        setupLanguageSpinner()
    }

    override fun setupData() {
        context?.let {
            presenter = SettingPresenter(
                this,
                LanguageRepository.getInstance(
                    LanguageLocalDataSource.getInstance(
                        SharePreferenceHelper.getInstance(it)
                    )
                ),
                CurrentCityRepository.getInstance(
                    CurrentCityLocalDataSource.getInstance(
                        SharePreferenceHelper.getInstance(it)
                    )
                )
            )
        }
        if (presenter?.getLanguage() == null) {
            presenter?.putLanguage(Locale.getDefault().language)
        }
        presenter?.start()
    }

    override fun initActions() {
        buttonBackSetting.setOnClickListener {
            fragmentManager?.removeFragment(this@SettingFragment)
        }
        showNotificationAboutDialog()
        changeItemLanguageSpinner()
        turnNotificationOnOff()
    }

    override fun changeLanguage(languageCode: String?) {
        languageCode?.let {
            val locale = Locale(it)
            Locale.setDefault(locale)
            val displayMetrics = resources.displayMetrics
            val configuration = resources.configuration
            configuration.locale = locale
            resources.updateConfiguration(configuration, displayMetrics)
        }
    }

    override fun showMessage(data: Any) {
        context?.showToast(data.toString())
    }

    private fun turnNotificationOnOff() {
        switchNotification.setOnCheckedChangeListener { _, _ ->
            context?.let {
                if (switchNotification.isChecked) {
                    presenter?.let { presenter ->
                        NotificationUtils.enableNotification(
                            it,
                            presenter.getLatitude(),
                            presenter.getLongitude()
                        )
                    }
                } else {
                    NotificationUtils.disableNotification(it)
                }
            }
        }
    }

    private fun setupLanguageSpinner() {
        val languageList = listOf(
            resources.getString(R.string.text_language_english),
            resources.getString(R.string.text_language_vietnamese)
        )
        val arrayAdapter =
            context?.let { ArrayAdapter(it, R.layout.item_language_spinner, languageList) }
        arrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerLanguage.apply {
            gravity = Gravity.END
            adapter = arrayAdapter
            when (presenter?.getLanguage()) {
                LOCALE_ENGLISH -> setSelection(ENGLISH_SPINNER_POSITION, false)
                LOCALE_VIETNAM -> setSelection(VIETNAMESE_SPINNER_POSITION, false)
            }
        }
    }

    private fun changeItemLanguageSpinner() {
        spinnerLanguage.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val language = presenter?.getLanguage()
                when (position) {
                    ENGLISH_SPINNER_POSITION -> {
                        presenter?.putLanguage(LOCALE_ENGLISH)
                    }
                    VIETNAMESE_SPINNER_POSITION -> {
                        presenter?.putLanguage(LOCALE_VIETNAM)
                    }
                }
                if (language != presenter?.getLanguage()) {
                    context?.let {
                        startActivity(
                            MainActivity.getIntent(it)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        )
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun showNotificationAboutDialog() {
        textAbout.setOnClickListener {
            AlertDialog.Builder(context, R.style.AboutDialogTheme)
                .setTitle(resources.getString(R.string.title_about))
                .setMessage(resources.getString(R.string.text_about_message))
                .setNegativeButton(resources.getString(R.string.text_ok)) { dialog, _ ->
                    dialog.cancel()
                }
                .create()
                .show()
        }
    }

    companion object {
        private const val LOCALE_ENGLISH = "en"
        private const val LOCALE_VIETNAM = "vi"
        private const val ENGLISH_SPINNER_POSITION = 0
        private const val VIETNAMESE_SPINNER_POSITION = 1
    }
}
