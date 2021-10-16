package br.com.crosslife.data.repositories.user

import br.com.crosslife.core.network.dto.DetailProfileDTO
import br.com.crosslife.core.network.dto.UserDTO
import br.com.crosslife.domain.models.DetailProfile
import br.com.crosslife.domain.models.User
import br.com.crosslife.extensions.orEmpty
import br.com.crosslife.extensions.orFalse

internal fun UserDTO.toDomain(): User = User(id.orEmpty(), user.orEmpty(), isAdmin.orFalse())

internal fun DetailProfileDTO.toDomain(): DetailProfile = DetailProfile(
    fatMass = fatMass,
    slimMass = slimMass,
    muscleMass = muscleMass,
    hydration = hydration,
    boneDensity = boneDensity,
    visceralFat = visceralFat,
    basalMetabolism = basalMetabolism,
)