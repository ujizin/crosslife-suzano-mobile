package br.com.crosslife.domain.repository

import br.com.crosslife.domain.model.WeeklyTrain
import kotlinx.coroutines.flow.Flow

interface WeeklyTrainRepository {
    fun fetchWeeklyTrains(): Flow<List<WeeklyTrain>>
    fun fetchWeeklyTrain(id: Int): Flow<WeeklyTrain>
}
