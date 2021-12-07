package br.com.crosslife

import android.app.Application
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.HiltAndroidApp

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@HiltAndroidApp
class CrossLifeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrossLifeMessageService.createNotificationChannel(this)
    }
}