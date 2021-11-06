package br.com.yujiyoshimine.domain.repository

import br.com.yujiyoshimine.domain.model.DetailProfile
import br.com.yujiyoshimine.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun fetchLogin(username: String, password: String): Flow<User>
    fun fetchLogout(): Flow<Unit>
    fun changePassword(username: String, password: String, newPassword: String): Flow<Unit>
    fun changePasswordWithToken(token: String, newPassword: String): Flow<Unit>
    fun fetchPassword(username: String): Flow<Unit>
    fun fetchDetailProfile(): Flow<DetailProfile>
}