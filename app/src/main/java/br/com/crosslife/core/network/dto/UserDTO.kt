package br.com.crosslife.core.network.dto

import br.com.crosslife.models.User

data class UserDTO(
    val id: Int,
    val user: String
) {

    fun toUser(): User = User(id, user)
}
