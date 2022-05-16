package com.android.exchange.repository

/**
 * Since native code not able to test using mockk library
 * This is a wrapper class to native method
 */
class NativeClassImpl : NativeBase  {
    override fun saveLog(path: String, time: Long, symbol: String, usdPrice: Double) {
        saveLogJNI(path, time, symbol,usdPrice)
    }
    external fun saveLogJNI(path:String, time:Long, symbol: String, usdPrice: Double )
}