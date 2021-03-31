package com.li.weatherapp.utils

import android.text.format.DateUtils

fun Long.convertToMilli(): Long = this * DateUtils.SECOND_IN_MILLIS
