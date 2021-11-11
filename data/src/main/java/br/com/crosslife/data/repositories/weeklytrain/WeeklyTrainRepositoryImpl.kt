package br.com.crosslife.data.repositories.weeklytrain

import br.com.crosslife.data.repositories.networkFlow
import br.com.crosslife.domain.model.WeeklyTrain
import br.com.crosslife.domain.repository.WeeklyTrainRepository
import br.com.crosslife.network.services.WeeklyTrainService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class WeeklyTrainRepositoryImpl(
    private val weeklyTrainService: WeeklyTrainService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeeklyTrainRepository {

    override fun fetchWeeklyTrains(): Flow<List<WeeklyTrain>> = networkFlow {
        val weeklyTrains = weeklyTrainService.fetchWeeklyTrains().toDomain()
        emit(weeklyTrains)
    }.flowOn(dispatcher)

    override fun fetchWeeklyTrain(id: Int): Flow<WeeklyTrain> = networkFlow {
        val weeklyTrain = weeklyTrainService.fetchWeeklyTrain(id).toDomain()
        emit(weeklyTrain)
    }
}
