package br.com.crosslife

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CrossLifeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrossLifeMessageService.createNotificationChannel(this)
    }
}