package com.project.fintrack.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.fintrack.data.dao.TransactionDao
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.utils.Converters

@Database(entities = [TransactionEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var instance: LocalDatabase? = null

       fun getInstance(context: Context): LocalDatabase {
           if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    LocalDatabase::class.java,
                    "fintrack_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
           }

           return instance!!
       }
    }
}
