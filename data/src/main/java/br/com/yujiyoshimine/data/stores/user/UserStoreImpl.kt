package br.com.crosslife.data.stores.user

import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.crosslife.core.local.datapreferences.DataPreferences
import br.com.yujiyoshimine.domain.store.UserStore
import kotlinx.coroutines.flow.Flow

class UserStoreImpl(private val dataStore: DataPreferences) :
    br.com.yujiyoshimine.domain.store.UserStore {

    private val tokenKey = stringPreferencesKey(USER_TOKEN_KEY)
    private val usernameKey = stringPreferencesKey(USERNAME_KEY)

    override fun getToken() = dataStore.get(tokenKey, "")

    override suspend fun setToken(token: String) {
        dataStore.set(tokenKey, token)
    }

    override fun getUsername(): Flow<String> = dataStore.get(usernameKey, "")

    override suspend fun setUsername(username: String) {
        dataStore.set(usernameKey, username)
    }

    companion object {
        private const val USER_TOKEN_KEY = "user_token"
        private const val USERNAME_KEY = "username_key"
    }
}