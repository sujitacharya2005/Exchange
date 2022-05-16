@file:JvmName("Utils")

package com.android.exchange.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Environment

const val FILE_NAME = "crypto_log.txt"

fun availableInternet(context: Context): Boolean {
    (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            ) ?: false
        } else {
            (@Suppress("DEPRECATION")
            return this.activeNetworkInfo?.isConnected ?: false)
        }
    }
}

fun getLogsDir(context: Context): String {
    val state = Environment.getExternalStorageState()
    val storageDir = if (Environment.MEDIA_MOUNTED == state) {
        //SD card (or partition) available
        context.getExternalFilesDir(null)
    } else {
        //Try internal storage
        context.filesDir
    }

    return storageDir?.absolutePath + FILE_NAME
}
