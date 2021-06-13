package br.com.crosslife.core.network.services

import br.com.crosslife.core.network.dto.UserDTO
import br.com.crosslife.data.repositories.user.payload.UserPayload

interface UserService {
    suspend fun fetchLogin(userPayload: UserPayload): UserDTO
}