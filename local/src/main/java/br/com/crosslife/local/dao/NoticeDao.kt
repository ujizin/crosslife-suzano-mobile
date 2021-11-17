package br.com.crosslife.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.crosslife.local.entities.Notice

@Dao
interface NoticeDao {

    @Query("SELECT * FROM notice")
    fun getAll(): List<Notice>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(notice: Collection<Notice>)
}
