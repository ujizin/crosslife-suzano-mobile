package br.com.crosslife.core.network.services

import br.com.crosslife.core.network.dto.UserDTO
import br.com.crosslife.core.network.payload.UserPayload
import br.com.crosslife.core.network.payload.PasswordPayload
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @POST("/login-aluno")
    suspend fun fetchLogin(@Body userPayload: UserPayload): UserDTO

    @PUT("/conta-aluno")
    suspend fun changePassword(@Body userPayload: UserPayload)

    @POST("/logout")
    suspend fun logout(): Response<Unit>

    @POST("/recuperar-senha")
    suspend fun forgotPassword(@Body userPayload: UserPayload): Response<Unit>

    @POST("/mudar-senha")
    suspend fun changePasswordWithToken(@Body passwordPayload: PasswordPayload)
}