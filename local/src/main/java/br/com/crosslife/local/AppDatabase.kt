package br.com.crosslife.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.crosslife.local.converter.Converters
import br.com.crosslife.local.dao.NoticeDao
import br.com.crosslife.local.dao.TrainDao
import br.com.crosslife.local.entities.Notice
import br.com.crosslife.local.entities.Train

@Database(
    entities = [Train::class, Notice::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trainDao(): TrainDao

    abstract fun noticeDao(): NoticeDao
}
