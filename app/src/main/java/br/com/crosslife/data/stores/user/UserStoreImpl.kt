package br.com.crosslife.data.stores.user

import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.crosslife.core.local.datapreferences.DataPreferences
import br.com.crosslife.domain.preferences.UserStore

class UserStoreImpl(private val dataStore: DataPreferences): UserStore {

    private val tokenKey = stringPreferencesKey(USER_TOKEN_KEY)

    override fun getToken() = dataStore.get(tokenKey, "")

    override suspend fun setToken(token: String) {
        dataStore.set(tokenKey, token)
    }

    companion object {
        private const val USER_TOKEN_KEY = "user_token"
    }
}