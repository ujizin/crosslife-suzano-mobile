package br.com.crosslife.local.store.train

import br.com.crosslife.local.entities.Train

interface TrainStore {
    suspend fun getTrains(): List<Train>
    suspend fun insertTrains(vararg train: Train)
}
