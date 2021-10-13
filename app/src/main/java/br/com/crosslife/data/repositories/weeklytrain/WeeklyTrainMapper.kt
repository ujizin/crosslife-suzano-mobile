package br.com.crosslife.data.repositories.weeklytrain

import br.com.crosslife.core.network.dto.WeeklyTrainDTO
import br.com.crosslife.domain.models.WeeklyTrain


internal fun List<WeeklyTrainDTO>.toDomain(): List<WeeklyTrain> = map { it.toDomain() }

internal fun WeeklyTrainDTO.toDomain(): WeeklyTrain = WeeklyTrain(id, title, dayOfWeek, description, imageUrl)