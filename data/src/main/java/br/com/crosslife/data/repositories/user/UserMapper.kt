package br.com.crosslife.data.repositories.user

import br.com.crosslife.domain.model.DetailProfile
import br.com.crosslife.domain.model.User
import br.com.crosslife.network.dto.DetailProfileDTO
import br.com.crosslife.network.dto.UserDTO

internal fun UserDTO.toDomain(): User = User(id ?: 0, user.orEmpty(), isAdmin ?: false)

internal fun DetailProfileDTO.toDomain(): DetailProfile = DetailProfile(
    fatMass = fatMass,
    slimMass = slimMass,
    muscleMass = muscleMass,
    hydration = hydration,
    boneDensity = boneDensity,
    visceralFat = visceralFat,
    basalMetabolism = basalMetabolism,
)