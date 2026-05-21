package com.example.cupcake.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [OrderEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CupcakeDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var Instance: CupcakeDatabase? = null

        fun getDatabase(context: Context): CupcakeDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CupcakeDatabase::class.java,
                    "cupcake_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}