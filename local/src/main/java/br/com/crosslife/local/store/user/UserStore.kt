package br.com.crosslife.local.store.user

import kotlinx.coroutines.flow.Flow

interface UserStore {
    fun getToken(): Flow<String>
    suspend fun setToken(token: String)

    fun getUsername(): Flow<String>
    suspend fun setUsername(username: String)

    fun isAdmin(): Flow<Boolean>
    suspend fun setAdmin(isAdmin: Boolean)
}