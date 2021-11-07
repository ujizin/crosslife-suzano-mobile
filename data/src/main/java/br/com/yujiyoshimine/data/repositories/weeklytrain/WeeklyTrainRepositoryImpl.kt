package br.com.yujiyoshimine.data.repositories.weeklytrain

import br.com.yujiyoshimine.domain.model.WeeklyTrain
import br.com.yujiyoshimine.domain.repository.WeeklyTrainRepository
import br.com.yujiyoshimine.network.services.WeeklyTrainService
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
