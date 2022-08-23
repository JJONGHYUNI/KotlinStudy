package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase(){
    abstract val sleepDatabaseDao: SleepDatabaseDao

    companion object{
        //메인 메모리에 저장 (여러 쓰레드에서 read&write 하는 상황에 원자성 보장)
        @Volatile
        private var INSTANCE : SleepDatabase? = null

        fun getInstance(context: Context): SleepDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance ==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return instance
            }

        }
    }
}