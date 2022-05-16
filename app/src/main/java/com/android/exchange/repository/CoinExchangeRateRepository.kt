package com.android.exchange.repository

import com.android.exchange.model.CryptoData

interface CoinExchangeRateRepository {
    suspend fun getRates() : List<CryptoData>
    suspend fun getLocal() : List<CryptoData>
}