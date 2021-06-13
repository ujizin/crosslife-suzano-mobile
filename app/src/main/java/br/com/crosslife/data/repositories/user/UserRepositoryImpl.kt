package br.com.crosslife.data.repositories.user

import br.com.crosslife.core.network.services.UserService
import br.com.crosslife.data.repositories.user.payload.UserPayload
import br.com.crosslife.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val userService: UserService,
) : UserRepository {

    override fun fetchLogin(username: String, password: String): Flow<User> = flow {
        val userPayload = UserPayload(username, password)
        val userDto = userService.fetchLogin(userPayload)
        emit(userDto.toUser())
    }
}