package com.android.exchange.repository

import android.content.Context
import com.android.exchange.api.CoinExchangeRateService
import com.android.exchange.db.CryptoDatabase
import com.android.exchange.model.CryptoData
import com.android.exchange.util.availableInternet
import com.android.exchange.util.getLogsDir


class CoinExchangeRateRepositoryImpl(
    private val coinExchangeRateService: CoinExchangeRateService,
    private val database: CryptoDatabase,
    private val applicationContext: Context,
    private val nativeBase: NativeBase
) : CoinExchangeRateRepository {

    override suspend fun getRates() : List<CryptoData> {
        if(availableInternet(applicationContext)) {
            fetchFromRemoteAndUpdateLocal()
        }

        val data = getLocalData()
        val time = System.currentTimeMillis()
        val path = getLogsDir(applicationContext)
        for(item in data) {
            nativeBase.saveLog(path, time, item.symbol, item.usdPrice)
        }
        return data
    }


    override suspend fun getLocal() : List<CryptoData> {
        return getLocalData()
    }

    private suspend fun fetchFromRemoteAndUpdateLocal() {
        val response = coinExchangeRateService.getMarkets()
        for(item in response) {
            item.id?.let {
                insert(CryptoData(it
                    , item.name?:"", item.symbol?:"", item.image?:""))

            }

        }
        val rates = coinExchangeRateService.getRates()
        for ((key, value) in rates) {
            update(key, value.usd?:0.0, value.btc?:0.0)
        }
    }

    private suspend fun insert(cryptoData: CryptoData) {
        database.cryptoDao().insert(cryptoData)
    }

    private suspend fun update(id: String, usdPrice:Double, btcPrice: Double) {
        database.cryptoDao().update(id, usdPrice, btcPrice)
    }

    private suspend fun getLocalData() : List<CryptoData> {
        return database.cryptoDao().getData()
    }
}