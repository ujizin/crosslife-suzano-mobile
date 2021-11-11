package br.com.crosslife.network.payload

import com.squareup.moshi.Json

data class UserPayload(
    @field:Json(name = "nome_usuario")
    val username: String,
    @field:Json(name = "senha")
    val password: String? = null,
    @field:Json(name = "nova_senha")
    val newPassword: String? = null,
)
