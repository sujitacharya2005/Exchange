package com.android.exchange.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.exchange.model.CryptoData

@Database(entities = [CryptoData::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao

    companion object {
        @Volatile
        private var INSTANCE: CryptoDatabase? = null

        fun getDatabase(context: Context): CryptoDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,
                        CryptoDatabase::class.java,
                        "cryptoDb")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}