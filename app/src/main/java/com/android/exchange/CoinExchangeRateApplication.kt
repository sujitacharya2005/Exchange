package com.android.exchange

import android.app.Application
import com.android.exchange.api.CoinExchangeRateService
import com.android.exchange.api.RetrofitHelper
import com.android.exchange.db.CryptoDatabase
import com.android.exchange.repository.CoinExchangeRateRepository
import com.android.exchange.repository.CoinExchangeRateRepositoryImpl
import com.android.exchange.repository.NativeClassImpl

class CoinExchangeRateApplication : Application() {
    lateinit var coinExchangeRateRepository: CoinExchangeRateRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val coinExchangeRateService = RetrofitHelper.retrofitClient().create(CoinExchangeRateService::class.java)
        val database = CryptoDatabase.getDatabase(applicationContext)
        val nativeBase = NativeClassImpl()
        coinExchangeRateRepository = CoinExchangeRateRepositoryImpl(coinExchangeRateService, database, applicationContext, nativeBase)
    }
}