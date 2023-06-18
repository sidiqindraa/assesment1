package org.d3if0108.piramidcount.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.d3if0108.piramidcount.db.LimasEntity

@Dao
interface LimasDao {
    @Insert
    fun insert(limas: LimasEntity)
    @Query("SELECT * FROM limas ORDER BY id DESC")
    fun getLastLimas(): LiveData<List<LimasEntity>>
    @Query("DELETE FROM limas")
    fun clearData()
}