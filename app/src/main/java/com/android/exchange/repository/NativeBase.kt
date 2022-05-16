package com.android.exchange.repository


/**
 * Since native code not able to test using mockk library
 * This is a wrapper interface to native method
 */
interface NativeBase {
    fun saveLog(path:String, time:Long, symbol: String, usdPrice: Double )
}