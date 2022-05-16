package com.android.exchange.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.exchange.model.CryptoData
import com.android.exchange.repository.CoinExchangeRateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinExchangeRateViewModel(
    private val coinExchangeRateRepository: CoinExchangeRateRepository,
) : ViewModel() {

    private val _coinsExchangeRateLiveData = MutableLiveData<List<CryptoData>>()
    val coinsExchangeRateLiveData: LiveData<List<CryptoData>>
        get() = _coinsExchangeRateLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val values = coinExchangeRateRepository
                .getLocal()
            val list = mutableListOf<CryptoData>()
            //Dummy data at 0th position to add header
            list.add(CryptoData("header", "", "", ""))
            list.addAll(values)
            _coinsExchangeRateLiveData.postValue(list)
        }
    }


    fun getRates() {
        viewModelScope.launch(Dispatchers.IO) {
            val values = coinExchangeRateRepository
                .getRates()
            val list = mutableListOf<CryptoData>()
            //Dummy data at 0th position to add header
            list.add(CryptoData("header", "", "", ""))
            list.addAll(values)
            _coinsExchangeRateLiveData.postValue(list)
        }
    }
}

