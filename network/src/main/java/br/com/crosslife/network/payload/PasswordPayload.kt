package br.com.crosslife.network.payload

import com.squareup.moshi.Json

data class PasswordPayload(
    @field:Json(name = "senha")
    val password: String? = null,
)
