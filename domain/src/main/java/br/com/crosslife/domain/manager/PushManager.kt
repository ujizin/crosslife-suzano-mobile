package br.com.crosslife.domain.manager

import android.content.Context

interface PushManager {
    fun sendNotification(context: Context, title: String, message: String)
}
