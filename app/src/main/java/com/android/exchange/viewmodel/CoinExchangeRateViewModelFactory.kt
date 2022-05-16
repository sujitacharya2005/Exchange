package com.android.exchange.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.exchange.repository.CoinExchangeRateRepository

class CoinExchangeRateViewModelFactory(
    private val repository: CoinExchangeRateRepository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoinExchangeRateViewModel(repository) as T
    }
}