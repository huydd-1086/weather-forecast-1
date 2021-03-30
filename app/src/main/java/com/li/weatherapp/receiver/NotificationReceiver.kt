package com.li.weatherapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.li.weatherapp.service.NotificationService

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        NotificationService.enqueueWork(context, intent)
    }
}
