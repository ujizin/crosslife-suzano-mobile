package br.com.crosslife.data.repositories.weeklytrain

import br.com.crosslife.domain.model.WeeklyTrain
import br.com.crosslife.local.entities.Train
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

internal fun List<WeeklyTrainDTO>.toLocal() = map { it.toLocal() }

internal fun WeeklyTrainDTO.toLocal(): Train = Train(
    id,
    title,
    dayOfWeek,
    description,
    imageUrl,
    videoUrl
)

internal fun List<Train>.localToDomain(): List<WeeklyTrain> = map { it.toDomain() }

internal fun Train.toDomain(): WeeklyTrain = WeeklyTrain(
    id,
    title,
    dayWeek,
    description,
    imageUrl,
    videoUrl,
)