package com.android.exchange.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto")
data class CryptoData(
    @PrimaryKey
    val id: String, //eg. bitcoin, ethereum
    val name: String, //eg. Bitcoin, Ethereum,
    val symbol: String, //eg. btc, eth
    val image: String, // eg. "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579"
    val usdPrice: Double = 0.0, // 28839,
    val btcPrice: Double = 0.0,  // 1.0
    )
