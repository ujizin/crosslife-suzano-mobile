package br.com.crosslife

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import br.com.crosslife.commons.theme.Green
import br.com.crosslife.domain.manager.PushManager
import br.com.crosslife.local.store.notification.NotificationStore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

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

        fun createNotificationChannel(context: Context) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "CrossLife",
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
            .setSmallIcon(R.drawable.ic_strength)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.VISIBILITY_PUBLIC)
            .build()
    }
}