package br.com.yujiyoshimine.network.services

import br.com.yujiyoshimine.network.dto.DetailProfileDTO
import br.com.yujiyoshimine.network.dto.UserDTO
import br.com.yujiyoshimine.network.payload.UserPayload
import br.com.yujiyoshimine.network.payload.PasswordPayload
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

    @PUT("/mudar-senha")
    suspend fun changePasswordWithToken(@Body passwordPayload: PasswordPayload)

    @GET("/avaliacao-aluno/{username}")
    suspend fun fetchDetailProfile(@Path("username") username: String): DetailProfileDTO
}