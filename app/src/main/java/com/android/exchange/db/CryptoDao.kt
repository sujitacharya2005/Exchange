package com.android.exchange.db

import androidx.room.*
import com.android.exchange.model.CryptoData


@Dao
interface CryptoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cryptoData: CryptoData)

    @Query("UPDATE crypto SET usdPrice = :usdPrice, btcPrice = :btcPrice WHERE id = :id")
    suspend fun update(id: String, usdPrice: Double, btcPrice: Double)

    @Query("SELECT * FROM crypto")
    suspend fun getData(): List<CryptoData>
}