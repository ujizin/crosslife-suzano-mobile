package br.com.crosslife.domain.repositories

import br.com.crosslife.domain.models.WeeklyTrain
import kotlinx.coroutines.flow.Flow

interface WeeklyTrainRepository {
    fun fetchWeeklyTrains(): Flow<List<WeeklyTrain>>
    fun fetchWeeklyTrain(id: Int): Flow<WeeklyTrain>
}
