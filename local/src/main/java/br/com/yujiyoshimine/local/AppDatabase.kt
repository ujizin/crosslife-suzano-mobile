package br.com.yujiyoshimine.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.yujiyoshimine.local.dao.TrainDao
import br.com.yujiyoshimine.local.entities.Train

@Database(
    entities = [
        Train::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trainDao(): TrainDao

}
