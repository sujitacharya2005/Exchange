package com.android.exchange.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.exchange.repository.CoinExchangeRateRepository
import com.android.exchange.repository.CoinExchangeRateRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.*


class CoinExchangeRateViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = TestCoroutineDispatcher()


    private lateinit var coinExchangeRateViewModel: CoinExchangeRateViewModel

    @MockK
    private lateinit var coinExchangeRateRepository: CoinExchangeRateRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)
        mockIODispatcher()
        coinExchangeRateViewModel = CoinExchangeRateViewModel(coinExchangeRateRepository)
    }

    @Test
    fun testGetRates() = runBlocking {
        coEvery { coinExchangeRateRepository.getRates() } returns mutableListOf()
        coinExchangeRateViewModel.getRates()
        Assert.assertEquals(coinExchangeRateViewModel.coinsExchangeRateLiveData.value!!.size, 1)
    }

    @After
    fun tearDown() {

    }

    private fun mockIODispatcher() {
        mockkStatic(Dispatchers::class)
        every { Dispatchers.IO } returns dispatcher
    }
}