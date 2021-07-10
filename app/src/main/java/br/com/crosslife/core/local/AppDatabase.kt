package br.com.crosslife.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.crosslife.core.local.dao.TrainDao
import br.com.crosslife.core.local.entities.Train

@Database(
    entities = [
        Train::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trainDao(): TrainDao

}
