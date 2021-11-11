package br.com.crosslife.data.repositories.weeklytrain

import br.com.crosslife.domain.model.WeeklyTrain
import br.com.crosslife.network.dto.WeeklyTrainDTO


internal fun List<WeeklyTrainDTO>.toDomain(): List<WeeklyTrain> = map { it.toDomain() }

internal fun WeeklyTrainDTO.toDomain(): WeeklyTrain = WeeklyTrain(
    id,
    title,
    dayOfWeek,
    description,
    imageUrl,
    videoUrl
)