package br.com.crosslife.core.network.payload

import com.squareup.moshi.Json

data class UserPayload(
    @field:Json(name = "nome_usuario")
    val username: String,
    @field:Json(name = "senha")
    val password: String,
    @field:Json(name = "novaSenha")
    val newPassword: String? = null,
)
