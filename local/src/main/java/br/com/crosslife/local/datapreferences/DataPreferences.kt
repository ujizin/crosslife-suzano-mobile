package br.com.crosslife.local.datapreferences

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataPreferences {
    fun <T> get(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> set(key: Preferences.Key<T>, value: T)
}