package br.com.yujiyoshimine.data.repositories.user

import br.com.yujiyoshimine.data.errors.EmptyError
import br.com.yujiyoshimine.domain.model.DetailProfile
import br.com.yujiyoshimine.domain.model.User
import br.com.yujiyoshimine.domain.repository.UserRepository
import br.com.yujiyoshimine.domain.store.UserStore
import br.com.yujiyoshimine.network.payload.PasswordPayload
import br.com.yujiyoshimine.network.payload.UserPayload
import br.com.yujiyoshimine.network.services.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

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

    override fun changePasswordWithToken(
        token: String,
        newPassword: String,
    ): Flow<Unit> = flow {
        check(newPassword.isNotEmpty()) { throw EmptyError() }
        val userTokenPayload = PasswordPayload(newPassword)
        userStore.setToken(token)
        userService.changePasswordWithToken(userTokenPayload)
        emit(Unit)
    }

    override fun fetchLogout() = flow {
        userStore.apply {
            setUsername("")
            setToken("")
        }
        userService.logout()
        emit(Unit)
    }

    override fun fetchPassword(username: String): Flow<Unit> = flow {
        val userPayload = UserPayload(username)
        userService.forgotPassword(userPayload)
        emit(Unit)
    }

    override fun fetchDetailProfile(): Flow<DetailProfile> = flow {
        userStore.getUsername().map { user ->
            val detailProfile = userService.fetchDetailProfile(user)
            emit(detailProfile.toDomain())
        }.collect()
    }
}
