package com.android.exchange

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.exchange.fragment.CoinExchangeRateFragment


private const val EXTERNAL_STORAGE_PERMISSION_CODE = 100
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    EXTERNAL_STORAGE_PERMISSION_CODE)
        } else {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction().add(
                    R.id.fragment_container,
                    CoinExchangeRateFragment.newInstance()
                ).commit()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == EXTERNAL_STORAGE_PERMISSION_CODE
            && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            supportFragmentManager.beginTransaction().add(
                R.id.fragment_container,
                CoinExchangeRateFragment.newInstance()
            ).commit()
        }
    }



    companion object {
        init {
            System.loadLibrary("exchange")
        }
    }

}