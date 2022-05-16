package com.android.exchange.repository

import android.content.Context
import android.net.ConnectivityManager
import com.android.exchange.api.CoinExchangeRateService
import com.android.exchange.data.MarketsResponse
import com.android.exchange.data.PriceResponse
import com.android.exchange.db.CryptoDatabase
import com.android.exchange.model.CryptoData
import com.android.exchange.util.availableInternet
import com.android.exchange.util.getLogsDir
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

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
    lateinit var nativeBase: NativeBase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)
        every { applicationContext.getSystemService(Context.TELECOM_SERVICE) } returns(connectivityManager)
        coinExchangeRateRepository = CoinExchangeRateRepositoryImpl(coinExchangeRateService, database, applicationContext, nativeBase)
    }

    @Test
    fun testGetRates() = runBlocking {
        mockValues()
        val data = coinExchangeRateRepository.getRates()
        assertEquals(data[0].name, "Bitcoin")
        assertEquals(data[0].usdPrice, 10.0)
        assertEquals(data[0].btcPrice, 20.0)
    }
    fun mockValues() {
        mockkStatic("com.android.exchange.util.Utils")
        every {
            availableInternet(applicationContext)
        } returns true
        every {
            getLogsDir(applicationContext)
        } returns ""
        val price =  mutableMapOf<String, PriceResponse>()
        price["btc"] = PriceResponse(10.0, 20.0 )
        coEvery { coinExchangeRateService.getRates() }  returns price
        val marketsResponse = MarketsResponse()
        (marketsResponse as MutableList<MarketsResponse.Item>).add(MarketsResponse.Item("bitcoin","btc","Bitcoin","xyz"))
        coEvery { coinExchangeRateService.getMarkets() }  returns marketsResponse
        coEvery { database.cryptoDao() }  returns mockk()
        coEvery { database.cryptoDao().getData() }  returns mutableListOf(CryptoData("bitcoin", "Bitcoin", "btc","xyz", 10.0, 20.0))
        every {  nativeBase.saveLog(any(), any(), any(), any()) } returns Unit
    }

    @Test
    fun testGetLocal() = runBlocking{
        mockValues()
        val data = coinExchangeRateRepository.getLocal()
        assertEquals(data[0], CryptoData("bitcoin", "Bitcoin", "btc","xyz", 10.0, 20.0))
    }

}