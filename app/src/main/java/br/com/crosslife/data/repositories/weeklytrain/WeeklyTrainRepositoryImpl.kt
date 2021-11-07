package br.com.crosslife.data.repositories.weeklytrain

import br.com.crosslife.core.network.services.WeeklyTrainService
import br.com.crosslife.domain.models.WeeklyTrain
import br.com.crosslife.domain.repositories.WeeklyTrainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeeklyTrainRepositoryImpl(
    private val weeklyTrainService: WeeklyTrainService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeeklyTrainRepository {

    override fun fetchWeeklyTrains(): Flow<List<WeeklyTrain>> = flow {
        val weeklyTrains = weeklyTrainService.fetchWeeklyTrains().toDomain()
        emit(weeklyTrains)
    }.flowOn(dispatcher)

    override fun fetchWeeklyTrain(id: Int): Flow<WeeklyTrain> = flow {
        val weeklyTrain = weeklyTrainService.fetchWeeklyTrain(id).toDomain()
        emit(weeklyTrain)
    }
}
