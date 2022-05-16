package com.android.exchange.repository

import android.content.Context
import android.os.Environment
import com.android.exchange.api.CoinExchangeRateService
import com.android.exchange.db.CryptoDatabase
import com.android.exchange.model.CryptoData
import com.android.exchange.util.availableInternet

const val FILE_NAME = "crypto_log.txt"

class CoinExchangeRateRepositoryImpl(
    private val coinExchangeRateService: CoinExchangeRateService,
    private val database: CryptoDatabase,
    private val applicationContext: Context,
) : CoinExchangeRateRepository {

    override suspend fun getRates() : List<CryptoData> {
        if(availableInternet(applicationContext)) {
            fetchFromRemoteAndUpdateLocal()
        }

        val data = getLocalData()
        val time = System.currentTimeMillis()
        val path = getLogsDir(applicationContext)
        for(item in data) {
            saveLogJNI(path, time, item.symbol, item.usdPrice)
        }
        return data
    }
    private fun getLogsDir(context: Context): String {
        val state = Environment.getExternalStorageState()
        val storageDir = if (Environment.MEDIA_MOUNTED == state) {
            //SD card (or partition) available
            println("someTag Hello")

            context.getExternalFilesDir(null)
        } else {
            //Try internal storage
            println("someTag hi")

            context.filesDir
        }

        return storageDir?.absolutePath + FILE_NAME
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

    external fun saveLogJNI(path:String, time:Long, symbol: String, usdPrice: Double )
}