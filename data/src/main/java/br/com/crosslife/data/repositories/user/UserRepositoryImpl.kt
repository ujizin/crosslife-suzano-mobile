package br.com.crosslife.data.repositories.user

import br.com.crosslife.data.repositories.networkFlow
import br.com.crosslife.domain.model.DetailProfile
import br.com.crosslife.domain.model.EmptyError
import br.com.crosslife.domain.model.User
import br.com.crosslife.domain.repository.UserRepository
import br.com.crosslife.local.store.user.UserStore
import br.com.crosslife.network.payload.PasswordPayload
import br.com.crosslife.network.payload.UserPayload
import br.com.crosslife.network.services.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

internal class UserRepositoryImpl(
    private val userService: UserService,
    private val userStore: UserStore,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UserRepository {

    override fun fetchLogin(username: String, password: String): Flow<User> = networkFlow {
        check(username.isNotEmpty() && password.isNotEmpty()) { throw EmptyError() }
        val userPayload = UserPayload(username, password)
        val userDto = userService.fetchLogin(userPayload)

        userStore.apply {
            setUsername(username)
            setToken(userDto.token)
            setAdmin(userDto.isAdmin)
        }
        emit(userDto.toDomain())
    }.flowOn(dispatcher)

    override fun changePassword(
        username: String,
        password: String,
        newPassword: String,
    ): Flow<Unit> = networkFlow {
        check(username.isNotEmpty() && password.isNotEmpty() && newPassword.isNotEmpty()) { throw EmptyError() }
        val userPayload = UserPayload(username, password, newPassword)
        userService.changePassword(userPayload)
        emit(Unit)
    }.flowOn(dispatcher)

    override fun changePasswordWithToken(
        token: String,
        newPassword: String,
    ): Flow<Unit> = networkFlow {
        check(newPassword.isNotEmpty()) { throw EmptyError() }
        val userTokenPayload = PasswordPayload(newPassword)
        userStore.setToken(token)
        userService.changePasswordWithToken(userTokenPayload)
        emit(Unit)
    }.flowOn(dispatcher)

    override fun fetchLogout() = networkFlow {
        userStore.apply {
            setUsername("")
            setToken("")
            setAdmin(false)
        }
        userService.logout()
        emit(Unit)
    }.flowOn(dispatcher)

    override fun fetchPassword(username: String): Flow<Unit> = networkFlow {
        val userPayload = UserPayload(username)
        userService.forgotPassword(userPayload)
        emit(Unit)
    }.flowOn(dispatcher)

    override fun fetchDetailProfile(): Flow<DetailProfile> = networkFlow {
        userStore.getUsername().collect { user ->
            val detailProfile = userService.fetchDetailProfile(user)
            emit(detailProfile.toDomain())
        }
    }.flowOn(dispatcher)

    override fun getToken() = userStore.getToken()

    override fun getUsername() = userStore.getUsername()
}
