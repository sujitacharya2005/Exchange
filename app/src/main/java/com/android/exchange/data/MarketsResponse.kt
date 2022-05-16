package com.android.exchange.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


class MarketsResponse : ArrayList<MarketsResponse.Item>() {
    @JsonClass(generateAdapter = true)
    data class Item(
        @Json(name = "id")
        val id: String?,
        @Json(name = "symbol")
        val symbol: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "image")
        val image: String?,
    )
}