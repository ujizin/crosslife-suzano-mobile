package br.com.crosslife.data.repositories.user

import br.com.crosslife.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun fetchLogin(username: String, password: String): Flow<User>
}