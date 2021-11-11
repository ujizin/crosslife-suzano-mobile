package br.com.crosslife.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.crosslife.local.dao.TrainDao
import br.com.crosslife.local.entities.Train

@Database(
    entities = [
        Train::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trainDao(): TrainDao

}
