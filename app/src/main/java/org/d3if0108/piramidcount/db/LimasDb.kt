package org.d3if0108.piramidcount.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LimasEntity::class], version = 1, exportSchema = false)
abstract class LimasDb : RoomDatabase() {
    abstract val dao: LimasDao
    companion object {
        @Volatile
        private var INSTANCE: LimasDb? = null
        fun getInstance(context: Context): LimasDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LimasDb::class.java,
                        "limas.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
