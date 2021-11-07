package br.com.yujiyoshimine.data.repositories.weeklytrain

import br.com.yujiyoshimine.domain.model.WeeklyTrain
import br.com.yujiyoshimine.network.dto.WeeklyTrainDTO


internal fun List<WeeklyTrainDTO>.toDomain(): List<WeeklyTrain> = map { it.toDomain() }

internal fun WeeklyTrainDTO.toDomain(): WeeklyTrain = WeeklyTrain(
    id,
    title,
    dayOfWeek,
    description,
    imageUrl,
    videoUrl
)