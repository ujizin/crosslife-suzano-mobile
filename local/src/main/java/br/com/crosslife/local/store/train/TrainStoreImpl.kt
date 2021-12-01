package br.com.crosslife.local.store.train

import br.com.crosslife.local.dao.TrainDao
import br.com.crosslife.local.entities.Train

class TrainStoreImpl(private val trainDao: TrainDao) : TrainStore {

    override suspend fun getTrains() = trainDao.getAll()

    override suspend fun insertTrains(trains: Collection<Train>) = trainDao.insertAll(trains)
}
