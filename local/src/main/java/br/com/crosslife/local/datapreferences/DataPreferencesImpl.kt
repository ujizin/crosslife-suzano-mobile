package br.com.crosslife.local.datapreferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.io.IOException

private const val PREFERENCES_NAME = "crosslife_preferences"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

internal class DataPreferencesImpl(context: Context) : DataPreferences {

    private val dataStore = context.dataStore

    override fun <T> get(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> {
        return dataStore.data
            .onStart { /* TODO log key value when start */ }
            .catch { exception -> if (exception is IOException) emit(emptyPreferences()) }
            .map { preferences -> preferences[key] ?: defaultValue }
    }

    override suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}