package br.com.crosslife.local.store.notification

import kotlinx.coroutines.flow.Flow

interface NotificationStore {
    fun getToken(): Flow<String>
    suspend fun setToken(token: String)
}