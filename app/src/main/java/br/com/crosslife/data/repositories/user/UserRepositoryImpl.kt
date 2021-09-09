package br.com.crosslife.data.repositories.user

import br.com.crosslife.core.network.payload.UserPayload
import br.com.crosslife.core.network.services.UserService
import br.com.crosslife.data.errors.EmptyError
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
        check(username.isNotEmpty() && password.isNotEmpty()) { throw EmptyError() }
        val userPayload = UserPayload(username, password)
        val userDto = userService.fetchLogin(userPayload)

        userStore.apply {
            setUsername(username)
            setToken(userDto.token)
        }
        emit(userDto.toDomain())
    }

    override fun changePassword(
        username: String,
        password: String,
        newPassword: String,
    ): Flow<Unit> = flow {
        check(username.isNotEmpty() && password.isNotEmpty() && newPassword.isNotEmpty()) { throw EmptyError() }
        val userPayload = UserPayload(username, password, newPassword)
        userService.changePassword(userPayload)
        emit(Unit)
    }

    override fun fetchLogout() = flow {
        userService.logout()
        emit(Unit)
    }
}
