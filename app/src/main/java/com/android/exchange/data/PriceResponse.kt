package com.android.exchange.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PriceResponse(
    @Json(name = "usd")
    val usd: Double?,
    @Json(name = "btc")
    val btc: Double?,
)