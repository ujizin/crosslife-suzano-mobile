package br.com.crosslife.data.repositories.weeklytrain

import br.com.crosslife.data.repositories.networkFlow
import br.com.crosslife.domain.model.WeeklyTrain
import br.com.crosslife.domain.repository.WeeklyTrainRepository
import br.com.crosslife.local.store.train.TrainStore
import br.com.crosslife.network.services.WeeklyTrainService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

internal class WeeklyTrainRepositoryImpl(
    private val weeklyTrainService: WeeklyTrainService,
    private val trainStore: TrainStore,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeeklyTrainRepository {

    override fun fetchWeeklyTrains(): Flow<List<WeeklyTrain>> = networkFlow {
        val weeklyTrains = weeklyTrainService.fetchWeeklyTrains()
        trainStore.insertTrains(weeklyTrains.toLocal())
        emit(weeklyTrains.toDomain())
    }.catch {
        val weeklyTrains = trainStore.getTrains()
        check(weeklyTrains.isNotEmpty()) { throw it }
        emit(weeklyTrains.localToDomain())
    }.flowOn(dispatcher)

    override fun fetchWeeklyTrain(id: Int): Flow<WeeklyTrain> = networkFlow {
        val weeklyTrain = weeklyTrainService.fetchWeeklyTrain(id).toDomain()
        emit(weeklyTrain)
    }.flowOn(dispatcher)
}
