package br.com.crosslife.data.repositories.user

import br.com.crosslife.core.network.dto.UserDTO
import br.com.crosslife.domain.models.User
import br.com.crosslife.extensions.orEmpty

internal fun UserDTO.toDomain(): User = User(id.orEmpty(), user.orEmpty())
