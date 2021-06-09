package br.com.crosslife.data.repositories.user

import br.com.crosslife.core.network.services.UserService

class UserRepositoryImpl(
    private val userService: UserService,
) : UserRepository {

}