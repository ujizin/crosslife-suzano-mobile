package br.com.crosslife.local.store.notification

import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.crosslife.local.datapreferences.DataPreferences

class NotificationStoreImpl(private val dataStore: DataPreferences) : NotificationStore {

    private val tokenKey = stringPreferencesKey(NOTIFICATION_TOKEN_KEY)

    override fun getToken() = dataStore.get(tokenKey, "")

    override suspend fun setToken(token: String) {
        dataStore.set(tokenKey, token)
    }

    companion object {
        private const val NOTIFICATION_TOKEN_KEY = "notification_token_key"
    }
}