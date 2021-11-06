package br.com.yujiyoshimine.domain.repository

import br.com.yujiyoshimine.domain.model.WeeklyTrain
import kotlinx.coroutines.flow.Flow

interface WeeklyTrainRepository {
    fun fetchWeeklyTrains(): Flow<List<WeeklyTrain>>
    fun fetchWeeklyTrain(id: Int): Flow<WeeklyTrain>
}
