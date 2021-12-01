package br.com.crosslife.local.store.user

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.crosslife.local.datapreferences.DataPreferences
import kotlinx.coroutines.flow.Flow

internal class UserStoreImpl(private val dataStore: DataPreferences) : UserStore {

    private val tokenKey = stringPreferencesKey(USER_TOKEN_KEY)
    private val usernameKey = stringPreferencesKey(USERNAME_KEY)
    private val isAdminKey = booleanPreferencesKey(IS_ADMIN_KEY)

    override fun getToken() = dataStore.get(tokenKey, "")

    override suspend fun setToken(token: String) {
        dataStore.set(tokenKey, token)
    }

    override fun getUsername(): Flow<String> = dataStore.get(usernameKey, "")

    override suspend fun setUsername(username: String) {
        dataStore.set(usernameKey, username)
    }

    override fun isAdmin(): Flow<Boolean> = dataStore.get(isAdminKey, false)

    override suspend fun setAdmin(isAdmin: Boolean) {
        dataStore.set(isAdminKey, isAdmin)
    }

    companion object {
        private const val USER_TOKEN_KEY = "user_token_key"
        private const val USERNAME_KEY = "username_key"
        private const val IS_ADMIN_KEY = "is_admin_key"
    }
}