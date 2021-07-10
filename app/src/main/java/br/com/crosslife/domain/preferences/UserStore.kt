package br.com.crosslife.domain.preferences

import kotlinx.coroutines.flow.Flow

interface UserStore {
    fun getToken(): Flow<String>
    suspend fun setToken(token: String)
}