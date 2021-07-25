package br.com.crosslife.data.repositories.user

import br.com.crosslife.core.network.payload.UserPayload
import br.com.crosslife.core.network.services.UserService
import br.com.crosslife.domain.models.User
import br.com.crosslife.domain.preferences.UserStore
import br.com.crosslife.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val userService: UserService,
    private val userStore: UserStore,
) : UserRepository {

    override fun fetchLogin(username: String, password: String): Flow<User> = flow {
        val userPayload = UserPayload(username, password)
        val userDto = userService.fetchLogin(userPayload)

        userStore.setToken(userDto.token)
        emit(userDto.toDomain())
    }
}