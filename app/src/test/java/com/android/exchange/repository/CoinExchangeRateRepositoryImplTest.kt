package com.android.exchange.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.telecom.TelecomManager
import com.android.exchange.api.CoinExchangeRateService
import com.android.exchange.data.MarketsResponse
import com.android.exchange.data.PriceResponse
import com.android.exchange.db.CryptoDatabase
import com.android.exchange.util.availableInternet
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class CoinExchangeRateRepositoryImplTest{

    private val connectivityManager: ConnectivityManager = mockk()

    lateinit var coinExchangeRateRepository: CoinExchangeRateRepository
    @MockK
    lateinit var coinExchangeRateService: CoinExchangeRateService
    @MockK
    lateinit var database: CryptoDatabase
    @MockK
    lateinit var applicationContext: Context

    @MockK
    lateinit var marketsResponse: PriceResponse

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)
        every { applicationContext.getSystemService(Context.TELECOM_SERVICE) } returns(connectivityManager)
        coinExchangeRateRepository = CoinExchangeRateRepositoryImpl(coinExchangeRateService, database, applicationContext)
    }

    @Test
    fun testGetRates() = runBlocking {

        mockkStatic("com.android.exchange.util.Utils")
        every {
            availableInternet(applicationContext)
        } returns true
        coEvery { coinExchangeRateService.getRates() }  returns mock()
        coEvery { coinExchangeRateService.getMarkets() }  returns mock1()
        coEvery { database.cryptoDao() }  returns mockk()
        coEvery { database.cryptoDao().getData() }  returns mutableListOf()

        val data = coinExchangeRateRepository.getRates()

    }

    fun mock() : Map<String, PriceResponse> {
        val m =  mutableMapOf<String, PriceResponse>()
        m["dd"] = marketsResponse
        return m
    }

    fun mock1() : MarketsResponse {
        val d = MarketsResponse()

        return d
    }

    @Test
    fun testGetLocal() {

    }

    @Test
    fun testSaveLogJNI() {

    }
}