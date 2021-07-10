package br.com.crosslife.core.network.services

import br.com.crosslife.core.network.dto.UserDTO
import br.com.crosslife.core.network.payload.UserPayload
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/login-aluno")
    suspend fun fetchLogin(@Body userPayload: UserPayload): UserDTO
}