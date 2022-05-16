package com.android.exchange.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.exchange.model.CryptoData

/**
 * This class responsible to check two list is same or not
 */
internal class DiffUtilCallBack : DiffUtil.ItemCallback<CryptoData>() {
    override fun areItemsTheSame(oldItem: CryptoData, newItem: CryptoData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CryptoData,
        newItem: CryptoData,
    ): Boolean {
        return oldItem == newItem
    }
}