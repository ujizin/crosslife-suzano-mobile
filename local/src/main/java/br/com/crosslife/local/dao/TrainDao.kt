package br.com.crosslife.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.crosslife.local.entities.Train

@Dao
interface TrainDao {

    @Query("SELECT * FROM train")
    fun getAll(): List<Train>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(train: Collection<Train>)
}
