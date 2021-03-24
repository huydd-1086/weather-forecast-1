package com.li.weatherapp.data.source.remote.utils

import android.os.AsyncTask
import com.li.weatherapp.data.source.utils.OnDataLoadCallback

@Suppress("DEPRECATION")
class RemoteAsyncTask<T>(
    private val callback: OnDataLoadCallback<T>,
    private val handle: () -> T
) : AsyncTask<Unit, Unit, T?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: Unit?): T? =
        try {
            handle()
        } catch (e: Exception) {
            exception = e
            null
        }

    override fun onPostExecute(result: T?) {
        super.onPostExecute(result)
        result?.let(callback::onSuccess) ?: (callback::onFail)
    }
}
