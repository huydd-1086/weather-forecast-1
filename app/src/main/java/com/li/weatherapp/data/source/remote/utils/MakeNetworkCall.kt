package com.li.weatherapp.data.source.remote.utils

import com.li.weatherapp.BuildConfig
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

fun makeNetworkCall(urlLink: String): String {
    val stringBuilder = StringBuilder()
    try {
        val url = URL(urlLink)
        val urlConnection = url.openConnection() as HttpURLConnection
        val inputStreamReader = InputStreamReader(urlConnection.inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        bufferedReader.forEachLine {
            stringBuilder.append(it)
        }
    } catch (e: IOException) {
        if (BuildConfig.DEBUG) e.printStackTrace()
    }
    return stringBuilder.toString()
}
