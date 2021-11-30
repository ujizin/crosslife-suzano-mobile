package br.com.crosslife.network.dto

import com.squareup.moshi.Json

data class UserDTO(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "user")
    val user: String?,
    @field:Json(name = "Token")
    val token: String,
    @field:Json(name = "Admin")
    val isAdmin: Boolean = false,
)
