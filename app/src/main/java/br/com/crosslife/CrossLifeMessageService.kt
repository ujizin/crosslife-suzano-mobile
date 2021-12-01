package br.com.crosslife

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import br.com.crosslife.commons.theme.Green
import br.com.crosslife.domain.manager.PushManager
import br.com.crosslife.local.store.notification.NotificationStore
import br.com.crosslife.navigation.BuildConfig
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
class CrossLifeMessageService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationStore: NotificationStore

    private fun parseRemoteMessage(remoteMessage: RemoteMessage): Pair<String, String> {
        val notification = remoteMessage.notification
        val title = notification?.title.orEmpty()
        val message = notification?.body.orEmpty()
        return Pair(title, message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        runBlocking {
            notificationStore.setToken(token)
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val (title, message) = parseRemoteMessage(remoteMessage)
        sendNotification(this, title, message)
    }

    companion object : PushManager {
        private const val NOTIFICATION_ID = 1
        private const val NOTIFICATION_NAME = "CrossLife"
        private const val CHANNEL_ID = "cross_life"

        override fun sendNotification(
            context: Context,
            title: String,
            message: String,
        ) {

            NotificationManagerCompat.from(context)
                .notify(
                    NOTIFICATION_ID,
                    getNotification(context, title, message)
                )

        }

        private fun getPendingIntent(context: Context) = TaskStackBuilder.create(context).run {
            val intent = Intent(
                Intent.ACTION_VIEW,
                BuildConfig.APP_URL.toUri(),
                context,
                MainActivity::class.java
            )
            addNextIntentWithParentStack(intent)
            getPendingIntent(PushManager.REQUEST_CODE, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        fun createNotificationChannel(context: Context) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                val channel = NotificationChannel(
                    CHANNEL_ID,
                    NOTIFICATION_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )

                notificationManager.createNotificationChannel(channel)
            }
        }

        private fun getNotification(
            context: Context,
            title: String,
            message: String
        ) = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(Green.toArgb())
            .setContentIntent(getPendingIntent(context))
            .setSmallIcon(R.drawable.ic_strength)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.VISIBILITY_PUBLIC)
            .build()
    }
}