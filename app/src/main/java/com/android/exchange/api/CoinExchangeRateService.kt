package com.android.exchange.api

import com.android.exchange.data.MarketsResponse
import com.android.exchange.data.PriceResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val COINS_IDS = "bitcoin,binancecoin,ethereum,basic-attention-token"
private const val CURRENCIES = "usd,btc"


interface CoinExchangeRateService {
    @GET("simple/price")
    suspend fun getRates(
        @Query("ids") ids: String = COINS_IDS,
        @Query("vs_currencies") currencies: String = CURRENCIES,
    ): Map<String, PriceResponse>

    @GET("coins/markets")
    suspend fun getMarkets(
        @Query("ids") ids: String = COINS_IDS,
        @Query("vs_currency") currency: String = "usd",
    ): MarketsResponse
}